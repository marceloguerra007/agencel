create database VIRGULA
Drop database VIRGULA
GO
USE VIRGULA
CREATE TABLE CLI_CONVENIO (
	CNV_IN_COD NUMERIC(5) PRIMARY KEY,
	CNV_ST_NOME VARCHAR(50)
	)
insert into CLI_CONVENIO values(1,'Unimed');
insert into CLI_CONVENIO values(2,'Bradesco');
insert into CLI_CONVENIO values(3,'SulAmerica');
insert into CLI_CONVENIO values(4,'Amil')
insert into CLI_CONVENIO values(5,'Dix');
insert into CLI_CONVENIO values(6,'Samsil');
insert into CLI_CONVENIO values(7,'Intermedica');
insert into CLI_CONVENIO values(8,'Medial');
insert into CLI_CONVENIO values(9,'Particular');



select * from cli_convenio

CREATE TABLE CLI_PACIENTE (
	PAC_IN_CODIGO NUMERIC(9) PRIMARY KEY,
	PAC_ST_NOME VARCHAR(50),
    PAC_DT_NASCIMENTO SMALLDATETIME,
	PAC_RE_PESO NUMERIC(5,2),
	PAC_RE_ALTURA NUMERIC(5,2),
	PAC_ST_SEXO CHAR(1),
	PAC_ST_CELULAR VARCHAR(12)
)

insert into cli_paciente values(1,'Joao Carlos da Silva','1975-29-12',65,1.75,'M','11-8019-0000');
insert into cli_paciente values(2,'Marina de Almeida Santos','1996-29-02',65,1.75,'F','11-8019-0001');
insert into cli_paciente values(3,'Roberta Teixeira Ynkys','1965-14-01',74,1.63,'F','11-8019-0002');
insert into cli_paciente values(4,'Abelardo Barbei Jr','2005-16-11',34,0.98,'M','11-8019-0003');
insert into cli_paciente values(5,'Abelardo Barbei','1974-07-01',107,2.01,'M','11-8019-0004');
insert into cli_paciente values(6,'Juraci Termeia','1950-15-06',65,1.57,'F','11-8019-0005');
insert into cli_paciente values(7,'Joaquina de Almeida','1957-30-09',87,1.99,'F','11-8019-0006');
insert into cli_paciente values(8,'Marcos Antonio de Abrantes','1988-01-04',62,1.65,'M','11-8019-0007');
insert into cli_paciente values(9,'Domingos Xavier de Castro','1993-05-05',78,1.88,'M','11-8019-0008');
insert into cli_paciente values(10,'Joao Carlos da Silva','1964-03-19',74,1.84,'M','11-8019-0009');


select * from cli_paciente


CREATE TABLE CLI_ESPECIALIDADE (
	ESP_IN_CODIGO NUMERIC(5) PRIMARY KEY,
	ESP_ST_NOME VARCHAR(30)
)

insert into cli_especialidade values (1,'Clinico Geral');
insert into cli_especialidade values (2,'Ortopedista');
insert into cli_especialidade values (3,'Pediatra');
insert into cli_especialidade values (4,'Dermatologista');
insert into cli_especialidade values (5,'Cardiologista');

select * from cli_especialidade

CREATE TABLE CLI_FERIADO (
	FER_DT_DATA smalldatetime PRIMARY KEY,
	FER_ST_NOME VARCHAR(50)
)

CREATE TABLE CLI_MEDICO (
	MED_IN_CODIGO NUMERIC(9) PRIMARY KEY,
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
)
hour
insert into CLI_MEDICO values (1,'Joaquim Silvestre',15.852,1,'Segunda',2,(get date)12.00,(get date)18.00,(get date)15.00,(get date)15.30);

select hour
select * from cli_medico

CREATE TABLE CLI_AGENDA (
	AGE_IN_CODIGO NUMERIC(9) PRIMARY KEY,
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
)

