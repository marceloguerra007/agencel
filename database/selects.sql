select e.esp_st_nome, m.* from cli_medico m, cli_especialidade e
where m.esp_in_cod = e.esp_in_codigo


select * from cli_especialidade