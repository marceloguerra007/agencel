--set dateformat DMY 
--insert de medicos
insert into CLI_MEDICO values ('Joaquim Silvestre','15.852',1,'2;3;5',30,'12:00:00','18:00','15:00:00','15:30:00');
insert into CLI_MEDICO values ('Maria Joaquina Sanchez','45.850',3,'4;5',15,'07:00:00','09:00:00',null,null);
insert into CLI_MEDICO values ('Antonio Chi','57.985',5,'2;3',60,'13:00:00','18:00:00','15:00:00','16:00:00');

select * from cli_especialidade

--agenda médico joaquim
--atende segunda,quarta,quinta 
--dia 10/12/2009 - quinta
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','10/12/2009','17:30:00','18:00:00',null);
-- Dia 14/12 - segunda
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/12/2009','17:00:00','18:00:00',null);
--dia 16/12 - quarta
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','16/12/2009','17:30:00','18:00:00',null);
-- Dia 17/11 - quinta
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','17/12/2009','17:00:00','18:00:00',null);
-- Dia 21/12 - segunda
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','21/12/2009','17:00:00','18:00:00',null);
-- Dia 23/12 - quarta
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','23/12/2009','17:00:00','18:00:00',null);
--semana do ano novo não trabalha
-- Dia 04/01/2010 - segunda
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','04/01/2010','17:00:00','18:00:00',null);
-- Dia 06/01/2010 - quarta
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','06/01/2010','17:00:00','18:00:00',null);
-- Dia 07/01/2010 - quinta
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','07/01/2010','17:00:00','18:00:00',null);
-- Dia 11/01/2010 - segunda
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','11/01/2010','17:00:00','18:00:00',null);
-- Dia 13/01/2010 - quarta
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','13/01/2010','17:00:00','18:00:00',null);
-- Dia 14/01/2010 - quinta
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','12:00:00','12:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','12:30:00','13:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','13:00:00','13:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','14:00:00','14:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','14:30:00','15:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','15:30:00','16:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','16:00:00','16:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','16:30:00','17:00:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','17:00:00','17:30:00',null);
insert into CLI_AGENDA values (1,null,null,'LV','14/01/2010','17:00:00','18:00:00',null);


-- Agenda Maria Joaquina
--atende quarta e quinta
--dia 10/12/2009 - quinta
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','10/12/2009','08:45:00','09:00:00',null);
--dia 16/12/2009 - quarta
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','16/12/2009','08:45:00','09:00:00',null);
--dia 17/12/2009 - quinta
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','17/12/2009','08:45:00','09:00:00',null);

--semana do natal e ano novo não trabalha
--dia 06/01/2010 - quarta
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','06/01/2010','08:45:00','09:00:00',null);
--dia 07/01/2010 - quinta
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','07/01/2010','08:45:00','09:00:00',null);
--dia 13/01/2010 - quarta
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','13/01/2010','08:45:00','09:00:00',null);
--dia 14/01/2010 - quinta
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','14/01/2010','08:45:00','09:00:00',null);
--dia 20/01/2010 - quarta
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:45:00','09:00:00',null);
--dia 21/01/2010 - quinta
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:00:00','07:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:15:00','07:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:30:00','07:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','07:45:00','08:00:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:00:00','08:15:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:15:00','08:30:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:30:00','08:45:00',null);
insert into CLI_AGENDA values (2,null,null,'LV','21/01/2010','08:45:00','09:00:00',null);

--Agenda do medico Antonio Chi
--atende segunda e terça
--dia 14/12/2009 - segunda
insert into CLI_AGENDA values (3,null,null,'LV','14/12/2009','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','14/12/2009','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','14/12/2009','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','14/12/2009','17:00:00','18:00:00',null);
--dia 15/12/2009 - terça
insert into CLI_AGENDA values (3,null,null,'LV','15/12/2009','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','15/12/2009','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','15/12/2009','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','15/12/2009','17:00:00','18:00:00',null);
--dia 21/12/2009 - segunda 
insert into CLI_AGENDA values (3,null,null,'LV','21/12/2009','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','21/12/2009','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','21/12/2009','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','21/12/2009','17:00:00','18:00:00',null);
--dia 22/12/2009 - terça
insert into CLI_AGENDA values (3,null,null,'LV','22/12/2009','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','22/12/2009','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','22/12/2009','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','22/12/2009','17:00:00','18:00:00',null);
--dia 28/12/2009 - segunda
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','17:00:00','18:00:00',null);
--dia 29/12/2009 - terça
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','29/12/2009','17:00:00','18:00:00',null);
--dia 04/01/2010 - segunda
insert into CLI_AGENDA values (3,null,null,'LV','04/01/2010','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','04/01/2010','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','04/01/2010','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','04/01/2010','17:00:00','18:00:00',null);
--dia 05/01/2010 - terça
insert into CLI_AGENDA values (3,null,null,'LV','05/01/2010','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','05/01/2010','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','05/01/2010','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','05/01/2010','17:00:00','18:00:00',null);
--dia 11/01/2010 - segunda
insert into CLI_AGENDA values (3,null,null,'LV','11/01/2010','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','11/01/2010','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','11/01/2010','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','11/01/2010','17:00:00','18:00:00',null);
--dia 12/01/2010 - terça
insert into CLI_AGENDA values (3,null,null,'LV','12/01/2010','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','12/01/2010','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','12/01/2010','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','12/01/2010','17:00:00','18:00:00',null);
--dia 18/01/2010 - segunda
insert into CLI_AGENDA values (3,null,null,'LV','18/01/2010','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','18/01/2010','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','18/01/2010','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','18/01/2010','17:00:00','18:00:00',null);
--dia 19/01/2010 - terça
insert into CLI_AGENDA values (3,null,null,'LV','19/01/2010','13:00:00','14:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','19/01/2010','14:00:00','15:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','19/01/2010','16:00:00','17:00:00',null);
insert into CLI_AGENDA values (3,null,null,'LV','19/01/2010','17:00:00','18:00:00',null);
