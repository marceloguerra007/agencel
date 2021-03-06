SELECT 
  M.MED_ST_NOME AS MEDICO, 
  CONVERT(VARCHAR(2), DATEPART(dd,A.AGE_DT_DATA))
    +'/'+CONVERT(VARCHAR(2), DATEPART(mm,A.AGE_DT_DATA))
    +'/'+CONVERT(VARCHAR(4), DATEPART(yy,A.AGE_DT_DATA)) AS DIA,
  CONVERT(VARCHAR(2), DATEPART(hh,AGE_DT_HORAINI))+':'+ CONVERT(VARCHAR(2),DATEPART(n, AGE_DT_HORAINI)) AS HORARIO,
  AGE_ST_SITUACAO AS SITUACAO
FROM CLI_AGENDA AS A, CLI_MEDICO AS M
WHERE PAC_IN_CODIGO = 1
AND M.MED_IN_CODIGO = A.MED_IN_CODIGO
AND AGE_ST_SITUACAO = 'RE'
ORDER BY AGE_DT_DATA DESC