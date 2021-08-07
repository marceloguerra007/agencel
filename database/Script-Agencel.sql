/*
- Campos de código das tabelas devem ser automáticos
- DATEPART
- DATENAME
DROP DATABASE CLINICA_ABC
*/ 
USE AGENCEL
GO
CREATE TABLE CLI_CONVENIO (
	CNV_IN_COD NUMERIC(5) PRIMARY KEY IDENTITY(1,1) NOT NULL,
	CNV_ST_NOME VARCHAR(50)
);
GO
insert into CLI_CONVENIO values('Unimed');
insert into CLI_CONVENIO values('Bradesco');
insert into CLI_CONVENIO values('SulAmerica');
insert into CLI_CONVENIO values('Amil')
insert into CLI_CONVENIO values('Dix');
insert into CLI_CONVENIO values('Samsil');
insert into CLI_CONVENIO values('Intermedica');
insert into CLI_CONVENIO values('Medial');
insert into CLI_CONVENIO values('Particular');


--select * from cli_convenio

CREATE TABLE CLI_PACIENTE (
	PAC_IN_CODIGO NUMERIC(9) PRIMARY KEY IDENTITY(1,1) NOT NULL,
	PAC_ST_NOME VARCHAR(50),
    PAC_DT_NASCIMENTO SMALLDATETIME,
	PAC_RE_PESO NUMERIC(5,2),
	PAC_RE_ALTURA NUMERIC(5,2),
	PAC_ST_SEXO CHAR(1),
	PAC_ST_CELULAR VARCHAR(12)
);
GO
SET DATEFORMAT YDM
GO
insert into cli_paciente values('Joao Carlos da Silva','1975/12/12',65,1.75,'M','11-8019-0000');
insert into cli_paciente values('Marina de Almeida Santos','1996-29-02',65,1.75,'F','11-8019-0001');
insert into cli_paciente values('Roberta Teixeira Ynkys','1965-14-01',74,1.63,'F','11-8019-0002');
insert into cli_paciente values('Abelardo Barbei Jr','2005-16-11',34,0.98,'M','11-8019-0003');
insert into cli_paciente values('Abelardo Barbei','1974-07-01',107,2.01,'M','11-8019-0004');
insert into cli_paciente values('Juraci Termeia','1950-15-06',65,1.57,'F','11-8019-0005');
insert into cli_paciente values('Joaquina de Almeida','1957-30-09',87,1.99,'F','11-8019-0006');
insert into cli_paciente values('Marcos Antonio de Abrantes','1988-01-04',62,1.65,'M','11-8019-0007');
insert into cli_paciente values('Domingos Xavier de Castro','1993-05-05',78,1.88,'M','11-8019-0008');
insert into cli_paciente values('Joao Carlos da Silva','1964-19-03',74,1.84,'M','11-8019-0009');


--select * from cli_paciente

CREATE TABLE CLI_ESPECIALIDADE (
	ESP_IN_CODIGO NUMERIC(5) PRIMARY KEY IDENTITY(1,1) NOT NULL,
	ESP_ST_NOME VARCHAR(30)
);
GO
insert into cli_especialidade values ('Clinico Geral');
insert into cli_especialidade values ('Ortopedista');
insert into cli_especialidade values ('Pediatra');
insert into cli_especialidade values ('Dermatologista');
insert into cli_especialidade values ('Cardiologista');

--select * from cli_especialidade

CREATE TABLE CLI_FERIADO (
	FER_DT_DATA smalldatetime PRIMARY KEY NOT NULL,
	FER_ST_NOME VARCHAR(50)
);
GO
INSERT INTO CLI_FERIADO VALUES ('2009-25-12','Natal');
INSERT INTO CLI_FERIADO VALUES ('2009-20-11','Consciência Negra');
INSERT INTO CLI_FERIADO VALUES ('2009-15-11','Proclamação da República');

--select * from CLI_FERIADO

CREATE TABLE CLI_MEDICO (
	MED_IN_CODIGO NUMERIC(9) PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MED_ST_NOME VARCHAR(50),
	MED_ST_CRM VARCHAR(50),
	ESP_IN_COD NUMERIC(5)
		constraint FK_Especialidade foreign key (esp_in_cod) references cli_especialidade(esp_in_codigo),
	MED_ST_DIASEM VARCHAR(20),
	MED_IN_ESCALA NUMERIC(5),
	MED_DT_HORAINI smalldatetime,
	MED_DT_HORAFIM smalldatetime,
	MED_DT_INTERINI smalldatetime,
	MED_DT_INTERFIM smalldatetime,
);
GO
insert into CLI_MEDICO values ('Joaquim Silvestre',15.852,1,'2;3;5',30,'12:00:00','18:00:00','15:00:00','15:30:00');
insert into CLI_MEDICO values ('Maria Joaquina',13.169,1,'6;7',15,'09:00:00','17:00:00','12:00:00','14:30:00');
insert into CLI_MEDICO values ('Antonio Chi',20.171,1,'3;5',20,'14:00:00','19:00:00','00:00:00','00:00:00');

insert into CLI_MEDICO values ('Pedro M. Santos',24.967,2,'3;5;6',20,'15:00:00','20:00:00','17:00:00','18:00:00');

insert into CLI_MEDICO values ('Mario S. Silva',21.077,3,'2;4;6',20,'07:00:00','16:00:00','11:00:00','13:00:00');
insert into CLI_MEDICO values ('Eloah Siqueira',20.171,3,'4;5',20,'08:00:00','15:00:00','12:00:00','13:30:00');

--select * from cli_medico

SELECT * FROM CLI_MEDICO WHERE ESP_IN_COD = 2 ORDER BY MED_ST_NOME

CREATE TABLE CLI_AGENDA (
	AGE_IN_CODIGO NUMERIC(9) PRIMARY KEY IDENTITY(1,1) NOT NULL,
	MED_IN_CODIGO NUMERIC(9)
		constraint FK_Medico foreign key (med_in_codigo) references cli_medico(med_in_codigo),
	PAC_IN_CODIGO NUMERIC(9)
		constraint FK_Paciente foreign key (pac_in_codigo) references cli_paciente(pac_in_codigo),
	CNV_IN_COD NUMERIC(5)
		constraint FK_Convenio foreign key (cnv_in_cod) references cli_convenio(cnv_in_cod),
	AGE_ST_SITUACAO CHAR(1),
	AGE_DT_DATA smalldatetime,
	AGE_DT_HORAINI smalldatetime,
	AGE_DT_HORAFIM smalldatetime,
	AGE_ST_OBS VARCHAR(200)
);


