package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Funcionario;
import com.projetobancodedados.projetobd.repository.FuncionarioRepository;
import com.projetobancodedados.projetobd.model.Apontamento;
import com.projetobancodedados.projetobd.model.Contrato;
import com.projetobancodedados.projetobd.model.Gestor;
import com.projetobancodedados.projetobd.model.Cliente;
import com.projetobancodedados.projetobd.repository.ApontamentoRepository;
import com.projetobancodedados.projetobd.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository employeeRepository;

    @Autowired
    private ApontamentoRepository apontamentoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping
    public List<Funcionario> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Relatório de produtividade
    @GetMapping("/relatorio-produtividade")
    public List<Map<String, Object>> relatorioProdutividade() {
        List<Funcionario> funcionarios = employeeRepository.findAll();
        List<Apontamento> apontamentos = apontamentoRepository.findAll();
        List<Contrato> contratos = contratoRepository.findAll();

        List<Map<String, Object>> relatorio = new ArrayList<>();

        for (Funcionario f : funcionarios) {
            Map<String, Object> dados = new LinkedHashMap<>();
            dados.put("nomeFuncionario", f.getNome());

            // Apontamentos do funcionário
            List<Apontamento> apFunc = apontamentos.stream()
                .filter(a -> a.getFuncionario() != null && Objects.equals(a.getFuncionario().getId_funcionario(), f.getId_funcionario()))
                .collect(Collectors.toList());

            // Total de horas por mês
            Map<String, Double> horasPorMes = new HashMap<>();
            Map<LocalDate, Double> horasPorDia = new HashMap<>();
            Set<Integer> atividades = new HashSet<>();

            for (Apontamento a : apFunc) {
                if (a.getData_apontamento() == null || a.getHora_inicio() == null || a.getHora_fim() == null) continue;

                LocalDate data;
                Date dataAp = a.getData_apontamento();
                if (dataAp instanceof java.sql.Date) {
                    data = ((java.sql.Date) dataAp).toLocalDate();
                } else {
                    data = dataAp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                YearMonth ym = YearMonth.from(data);
                String mes = ym.toString();

                double horas = (a.getHora_fim().getTime() - a.getHora_inicio().getTime()) / (1000.0 * 60 * 60);
                if (horas < 0) horas = 0;

                horasPorMes.put(mes, horasPorMes.getOrDefault(mes, 0.0) + horas);
                horasPorDia.put(data, horasPorDia.getOrDefault(data, 0.0) + horas);

                if (a.getAtividade() != null && a.getAtividade().getId_atividade() != null)
                    atividades.add(a.getAtividade().getId_atividade());
            }
            dados.put("horasPorMes", horasPorMes);

            // Média de horas por dia trabalhado
            double mediaHorasDia = horasPorDia.isEmpty() ? 0.0 :
                horasPorDia.values().stream().mapToDouble(Double::doubleValue).sum() / horasPorDia.size();
            dados.put("mediaHorasPorDia", Math.round(mediaHorasDia * 100.0) / 100.0);

            // Número de atividades diferentes
            dados.put("numeroAtividades", atividades.size());

            // Nome do gestor responsável e cliente do contrato vinculado (se houver)
            Optional<Contrato> contratoOpt = contratos.stream()
                .filter(c -> c.getFuncionario() != null && Objects.equals(c.getFuncionario().getId_funcionario(), f.getId_funcionario()))
                .findFirst();

            if (contratoOpt.isPresent()) {
                Contrato contrato = contratoOpt.get();
                Gestor gestor = contrato.getGestor();
                Cliente cliente = contrato.getCliente();
                dados.put("gestorResponsavel", gestor != null ? gestor.getGrupoGerido() : null);
                dados.put("clienteContrato", cliente != null ? cliente.getNome_cliente() : null);
            } else {
                dados.put("gestorResponsavel", null);
                dados.put("clienteContrato", null);
            }

            relatorio.add(dados);
        }
        return relatorio;
    }
}