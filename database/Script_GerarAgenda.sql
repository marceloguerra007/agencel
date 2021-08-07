/*

SET DATEFORMAT DMY
exec SP_GERARAGENDA 1,'01/12/2009','15/12/2009'

SELECT CONVERT(VARCHAR(12),GETDATE(),101) AS '101',CONVERT(VARCHAR(12),GETDATE(),102) AS '102',CONVERT(VARCHAR(12),GETDATE(),103) AS '103',CONVERT(VARCHAR(12),GETDATE(),104) AS '104',CONVERT(VARCHAR(12),GETDATE(),105) AS '105',CONVERT(VARCHAR(12),GETDATE(),106) AS '106',CONVERT(VARCHAR(12),GETDATE(),107) AS '107',CONVERT(VARCHAR(12),GETDATE(),108) AS '108',CONVERT(VARCHAR(12),GETDATE(),109) AS '109',CONVERT(VARCHAR(12),GETDATE(),110) AS '110',CONVERT(VARCHAR(12),GETDATE(),111) AS '111',CONVERT(VARCHAR(12),GETDATE(),112) AS '112',CONVERT(VARCHAR(12),GETDATE(),113) AS '113',CONVERT(VARCHAR(12),GETDATE(),114) AS '114',CONVERT(VARCHAR(12),GETDATE(),120) AS '120',CONVERT(VARCHAR(12),GETDATE(),121) AS '121'

SELECT  DATENAME (YEAR, GETDATE()) AS  ANO,                        DATENAME (MONTH, GETDATE()) AS  MES,                        DATENAME (DAY, GETDATE()) AS  DIA,                        DATENAME (DAYOFYEAR, GETDATE()) AS  DIA_ANO,                        DATENAME (WEEK, GETDATE()) AS  SEMANA,                        DATENAME (HOUR, GETDATE()) AS  HORA,                        DATENAME (MINUTE, GETDATE()) AS MINUTO

select * from cli_medico where med_in_codigo = 1

--Dia da semana
Select Datepart(dw,'06/12/2009') as dom ,Datepart(dw,'07/12/2009') as seg, 
       Datepart(dw,'08/12/2009') as ter ,Datepart(dw,'09/12/2009') as qua, 
       Datepart(dw,'10/12/2009') as qui ,Datepart(dw,'11/12/2009') as sex,
       Datepart(dw,'12/12/2009') as sab 

--DATEDIFF  retorna um INTEITO como resultado de operações
--entre dua datas
DECLARE         @DT_EXEMPLO DATETIME
DECLARE         @DT_EXEMPLO2 DATETIME
SET             @DT_EXEMPLO = '05/12/2009'
SET             @DT_EXEMPLO2 = '06/12/2009'
SELECT  DATEDIFF(YEAR, @DT_EXEMPLO , @DT_EXEMPLO2) AS ANO,
                        DATEDIFF(MONTH, @DT_EXEMPLO , @DT_EXEMPLO2) AS MES,                        
DATEDIFF(DAY, @DT_EXEMPLO , @DT_EXEMPLO2) AS DIAS  

----DATEADD retorna um DATETIME como resultado de adição entre datas--
SELECT  DATEADD(DAY, -10 , GETDATE()) AS DATA_ATUAL_MENOS_10_DIAS,                        
DATEADD(DAY, 10 , GETDATE()) AS DATA_ATUAL_MAIS_10_DIAS,                        
DATEADD(YEAR, -10 , GETDATE()) AS DATA_ATUAL_MENOS_10_ANOS,                        
DATEADD(YEAR, 10 , GETDATE()) AS DATA_ATUAL_MAIS_10_ANOS

*/

ALTER procedure [dbo].[SP_GERARAGENDA] 
(
  @pMedico int,
  @pDataIni Datetime,
  @pDataFinal Datetime
)
AS
BEGIN
SET NOCOUNT ON -- Não exibe mais o rows affected após inserir

PRINT 'Inicio Gerar Agenda'

DECLARE @DIASSEMANAS nchar(20)
DECLARE @ESCALA int
DECLARE @MED_DT_HORAINI DATETIME
DECLARE @MED_DT_HORAFIM DATETIME
DECLARE @MED_DT_INTERINI DATETIME
DECLARE @MED_DT_INTERFIM DATETIME

SET @ESCALA = 0

/*
SELECT @NR_LOGIN = Count(USU_IN_CODIGO) 
FROM LOJ_USUARIO
WHERE USU_ST_LOGIN = @pLogin
*/

SELECT 
  @DIASSEMANAS = MED_ST_DIASEM,
  @ESCALA = MED_IN_ESCALA,
  @MED_DT_HORAINI = MED_DT_HORAINI,
  @MED_DT_HORAFIM = MED_DT_HORAFIM,
  @MED_DT_INTERINI = MED_DT_INTERINI,
  @MED_DT_INTERFIM = MED_DT_INTERFIM
FROM CLI_MEDICO
WHERE MED_IN_CODIGO = @pMedico

PRINT 'Escala do Medico em minutos = '+convert(VARCHAR(10), @ESCALA)

/*
IF @NR_LOGIN > 0 
BEGIN
   RAISERROR('O login informado já existe!',15,1)
   RETURN
END

IF LEN(@pNome) = 0 OR LEN(@pLogin) = 0 OR LEN(@pSenha) = 0
   BEGIN
 RAISERROR('Os parâmetros pNome, pLogin e pSenha são obrigatórios',15,1) 
   END
ELSE
   BEGIN
     --Insert into loj_usuario (USU_ST_NOME, USU_ST_LOGIN, USU_BI_SENHA)
     --values (@pNome, @pLogin, PWDENCRYPT(CONVERT(varbinary,@pSenha)))
   END
*/
	DECLARE @DataCorrente DATETIME
	DECLARE @vDiaSemana INT
	SET @DataCorrente = @pDataIni
	SET @pDataFinal = DATEADD(DAY, 1 , @pDataFinal) -- Adicionando mais um dia a data final pra que seja gerada agenda para a mesma
	SET @vDiaSemana = 0

	PRINT 'antes do while da data'

	WHILE ( DATEDIFF(DAY, @DataCorrente, @pDataFinal) > 0 )
	BEGIN
		PRINT 'Data Corrente='+convert(VARCHAR(20),@DataCorrente, 103)
		
		-- Verificando o dia da semana da data
		SET @vDiaSemana = Datepart(dw,@DataCorrente)		
		
		IF CHARINDEX(CONVERT(VARCHAR(1), @vDiaSemana),@DIASSEMANAS) > 0
		BEGIN
			PRINT 'Dia da semana que o médico atende - OK ' + convert(VARCHAR(20),@DataCorrente, 103)
		END 
		
		SET @DataCorrente = DATEADD(DAY, 1 , @DataCorrente)
	END

END