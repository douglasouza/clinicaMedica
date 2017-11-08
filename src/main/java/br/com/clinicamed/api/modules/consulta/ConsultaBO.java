package br.com.clinicamed.api.modules.consulta;

import br.com.clinicamed.api.modules.consulta.horarioconsulta.Horario;
import br.com.clinicamed.api.modules.consulta.horarioconsulta.HorarioRepository;
import br.com.clinicamed.api.modules.medico.Medico;
import br.com.clinicamed.api.modules.medico.MedicoRepository;
import br.com.clinicamed.api.modules.paciente.Paciente;
import br.com.clinicamed.api.modules.paciente.PacienteRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ConsultaBO {

    @Autowired
    private ConsultaRepository repo;

    @Autowired
    private HorarioRepository horarioRepo;

    @Autowired
    private MedicoRepository medicoRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    public Object pesquisarConsulta(String nomeMedicoPaciente, Date dataInicial, Date dataFinal) {
        Iterable<Consulta> consultas;
        if (StringUtils.isEmpty(nomeMedicoPaciente) && dataInicial == null && dataFinal == null)
            consultas = repo.findAll();
        else
            consultas = repo.findAll(getBooleanExpression(nomeMedicoPaciente, dataInicial, dataFinal));

        return getConsultasDTO(consultas);
    }

    private BooleanExpression getBooleanExpression(String nomeMedicoPaciente, Date dataInicial, Date dataFinal) {
        BooleanExpression booleanExpression = null;
        if (!StringUtils.isEmpty(nomeMedicoPaciente))
            booleanExpression = QConsulta.consulta.medico.nome.containsIgnoreCase(nomeMedicoPaciente)
                    .or(QConsulta.consulta.paciente.nome.containsIgnoreCase(nomeMedicoPaciente));

        if (dataInicial != null)
            booleanExpression = QConsulta.consulta.dataConsulta.after(dataInicial);

        if (dataFinal != null)
            booleanExpression = QConsulta.consulta.dataConsulta.before(dataFinal);

        return booleanExpression;
    }

    private List<ConsultaDTO> getConsultasDTO(Iterable<Consulta> consultas) {
        List<ConsultaDTO> consultasDTO = new ArrayList<>();
        for (Consulta consulta : consultas) {
            ConsultaDTO consultaDTO = new ConsultaDTO();
            consultaDTO.setId(consulta.getId());
            consultaDTO.setNomeMedico(consulta.getMedico().getNome());
            consultaDTO.setNomePaciente(consulta.getPaciente().getNome());
            consultaDTO.setDataHoraConsulta(new SimpleDateFormat("dd/MM/yyy").format(consulta.getDataConsulta())
                    + " " + consulta.getHorarioConsulta().getHoraConsulta());
            consultasDTO.add(consultaDTO);
        }

        return consultasDTO;
    }

    public Consulta inserirConsulta(ConsultaDTO consultaDTO) {
        Paciente paciente = pacienteRepo.findOne(consultaDTO.getIdPaciente());
        Medico medico = medicoRepo.findOne(consultaDTO.getIdMedico());
        Horario horario = horarioRepo.findOne(consultaDTO.getIdHorarioConsulta());

        Consulta consulta = new Consulta();
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setHorarioConsulta(horario);

        return repo.saveAndFlush(consulta);
    }

    public Consulta atualizarConsulta(Consulta updatedConsulta, Long id) {
        return repo.saveAndFlush(updatedConsulta);
    }

    public void removerConsulta(Long idConsulta) {
        repo.delete(idConsulta);
    }
}
