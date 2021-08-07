package principal;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import conexao.Con_Servlet;
import conexao.Registros;

public class ControlePrincipal_Mid extends MIDlet implements CommandListener, ItemStateListener
{
	protected Display display = null;
	private Form form = null;
	private List formlist = null;
	private TextBox formMsg = null;
	private Alert alertConfirmacao = null;
	
	private Command cmdAjuda = null, cmdVoltar = null, cmdSair = null;
	private Command cmdOkAcessoPac = null;
	private Command cmdOkMarcar = null;
	private Command cmdOkPendente = null;
	private Command cmdCancPendente = null;
	private Command cmdOkRealizadas = null;
	private Command cmdOkConfirmacao = null,cmdCancConfirmacao = null;
	private Command cmdCancMensagem = null, cmdOkMensagem = null, cmdTentarMensagem = null;

	private ChoiceGroup cgPaciente = null, cgEspec = null, cgMedico = null;
	private ChoiceGroup cgHorarios = null, cgAno = null, cgMes = null, cgDia=null;
	private TextField txtCodAcesso = null;
	
	// Controle de Opções
	private final String MN_MARCAR = "Marcar Consulta";
	private final String MN_VISUALIZAR = "Visualizar Consultas";
	private final String MN_PENDENTES = "Consultas Pendentes";
	private final String MN_REALIZADAS = "Consultas Realizadas";
	private final String MN_SOBRE = "Sobre";
	
	// Controle de Telas
	private int iTelaAtual = 0;
	private final int TL_MARCAR = 1;
	private final int TL_VISUALIZAR = 2;
	private final int TL_PENDENTES = 3;
	
	// Controle das operações
	private int iProcessoAtual = 0;
	private final int PC_OK = 1;
	private final int PC_CANCELAR = 2;
	
	// Controle de Choice group
	private final String CG_SELECIONE = "<Selecione>";
	private final String CG_TODAS = "<Todas>";
	
	// Controle de Conexão Servidor
	public final int ENVIARDADOS = 1;
	public final int RECEBERDADOS = 2;
	public final int TIMEOUT = 20;
	
	// Controle Geral
	private String sCabecalhoPadrao = "Clinica: Abc e Xyz.\n";
	private String sEndServidor = "http://localhost:8080/TomcatServletAgencel/";
	//private String sEndServidor = "http://SATURNO-PC:8080/ServletAgencel/";
	//private String sEndServidor = "http://SATURNO-PC:8080/ServletAgencel/";
	//private String sEndServidor = "http://192.168.1.10:8080/ServletAgencel/";
	
	// Variaveis Globais
	// Obs: Necessário ser global pois é utilizado em métodos chamados por eventos.
	private Vector vecDados=null; 	// Vector de dados genericos
	private int iCodMedSel=0, iCodPacSel=0;
	private int iMesesApos = 3;
	
	public ControlePrincipal_Mid() 
	{
		display = Display.getDisplay(this);
		
		cmdOkConfirmacao = new Command("Confirmar",Command.OK, 1);
		cmdCancConfirmacao = new Command("Cancelar", Command.BACK, 0);
		cmdCancMensagem = new Command("Cancelar", Command.BACK, 0);
		cmdOkMensagem = new Command("Ok", Command.OK, 1);
		cmdTentarMensagem = new Command("Tentar", Command.OK, 1);
		
		// Telas padrões utilizadas por várias telas do sistema.
		formMsg = new TextBox("Mensagem","",300,TextField.UNEDITABLE);
		alertConfirmacao = new Alert("Confirmacao", "", null, AlertType.CONFIRMATION);
	}

	protected void destroyApp(boolean arg0) 
	{
		notifyDestroyed();
	}

	protected void pauseApp() 
	{

	}

	protected void startApp() throws MIDletStateChangeException 
	{
		telaAcessoPaciente();
	}
	
	protected void telaAcessoPaciente()
	{
		form = new Form("Agencel - Acesso");
		cmdSair = new Command("Sair", Command.EXIT, 0);
		cmdAjuda = new Command("Ajuda", Command.HELP,0);
		cmdOkAcessoPac = new Command("Ok", Command.OK,1);
		cgPaciente = new ChoiceGroup("", ChoiceGroup.POPUP,new String[] {"<Novo>"}, null);
		txtCodAcesso = new TextField("Codigo Acesso","",10,TextField.ANY);

		// Comandos
		form.addCommand(cmdSair);
		form.addCommand(cmdAjuda);
		form.addCommand(cmdOkAcessoPac);
		form.setCommandListener(this);
		
		// Objetos
		form.append(sCabecalhoPadrao);
		form.append("Seja bem vindo\n\nPaciente");
		form.append(cgPaciente);
		form.append(txtCodAcesso);
		form.setItemStateListener(this);

		// Outros
		Ajuda_Padrao.setTexto("Escolha um paciente na lista ou caso seja seu primeiro acesso, escolha a opcao <Novo>, informe seu codigo de acesso e clique em Ok.");
		
		display.setCurrent(form);
		
		// Carregar pacientes armazenados no celular.
		carregaPacientesGravadosCel();
	}
	
	// Menu Paciente
	protected void telaMenuPaciente()
	{
		formlist = new List("Agencel - Menu Principal", List.IMPLICIT);
		cmdSair = new Command("Sair", Command.EXIT, 0);
		cmdAjuda = new Command("Ajuda", Command.HELP,0);
		
		// Comandos
		formlist.addCommand(cmdSair);
		formlist.addCommand(cmdAjuda);
		formlist.setCommandListener(this);
		
		// Objetos
		//formlist.append(sCabecalhoPadrao);
		
		formlist.append(MN_MARCAR,null);
		formlist.append(MN_VISUALIZAR,null);
		formlist.append(MN_SOBRE, null);

		// Outros
		Ajuda_Padrao.setTexto("Escolha uma opção da lista. A opção Marcar permite marcar uma nova consulta e opção Visualizar permite consultar as consultas marcadas e já realizadas");
		
		display.setCurrent(formlist);
	}
	
	// Tela de Marcar Consulta
	protected void telaMarcarConsulta()
	{
		form = new Form("Agencel - Marcar Consulta");
		cmdVoltar = new Command("Voltar", Command.BACK, 0);
		cmdAjuda = new Command("Ajuda", Command.HELP,0);
		cmdOkMarcar = new Command("Ok", Command.OK,1);
		cgEspec = new ChoiceGroup("Especialidades", ChoiceGroup.POPUP, new String[] {CG_SELECIONE}, null);
		cgMedico = new ChoiceGroup("Medicos", ChoiceGroup.POPUP, new String[] {CG_SELECIONE}, null);
		cgDia = new ChoiceGroup("Dia",ChoiceGroup.POPUP); 
		cgMes = new ChoiceGroup("Mes",ChoiceGroup.POPUP); 
		cgAno = new ChoiceGroup("Ano",ChoiceGroup.POPUP); 
		cgHorarios = new ChoiceGroup("Horarios Disponiveis",ChoiceGroup.EXCLUSIVE); 
		
		// Comandos
		form.addCommand(cmdVoltar);
		form.addCommand(cmdOkMarcar);
		form.addCommand(cmdAjuda);
		form.setCommandListener(this);
		
		// Objetos
		form.append(sCabecalhoPadrao);
		form.append(cgEspec);
		form.setItemStateListener(this);
		
		// Outros
		Ajuda_Padrao.setTexto("Para marcar uma consulta, escolha a especialidade e depois o médico. Após escolher o médico será exibida uma lista de dias e horários disponiveis. Escolha o horário e confirme");
		iTelaAtual = TL_MARCAR;
		
		display.setCurrent(form);
		
		ajustaEspecialidadesMarcarConsulta();
		cgEspec.append(CG_TODAS, null);

	}
	
	// Tela de submenu da opção Visualizar ()
	protected void telaSubMenuVisualizar()
	{
		formlist = new List("Agencel - Visualizar Consultas", List.IMPLICIT);
		cmdVoltar = new Command("Voltar", Command.BACK, 0);
		cmdAjuda = new Command("Ajuda", Command.HELP,1);
		
		// Comandos
		formlist.addCommand(cmdVoltar);
		formlist.addCommand(cmdAjuda);
		formlist.setCommandListener(this);
		
		// Objetos
		formlist.append(MN_PENDENTES,null);
		formlist.append(MN_REALIZADAS,null);
		
		// Outros
		Ajuda_Padrao.setTexto("Escolha uma opção da lista. A opção Pendentes permite visualizar as consultas a serem realizadas para confirmar, cancelar ou somente visualizar. A opção Realizadas permite visualizar as consultas já realizadas");
		iTelaAtual = TL_VISUALIZAR;
		
		display.setCurrent(formlist);
	}
	
	protected void telaConsultasPendentes()
	{
		form = new Form("Agencel - Consultas Pendentes");
		cmdVoltar = new Command("Voltar", Command.BACK, 0);
		cmdAjuda = new Command("Ajuda", Command.HELP,0);
		cmdOkPendente = new Command("Confirmar", Command.OK,1);
		cmdCancPendente = new Command("Cancelar", Command.CANCEL,2);

		// Comandos
		form.addCommand(cmdVoltar);
		form.addCommand(cmdAjuda);

		form.append(sCabecalhoPadrao);
		
		form.addCommand(cmdOkPendente);
		form.addCommand(cmdCancPendente);
		form.setCommandListener(this);
		
		ajustaHorarioConsultasPendentes();
		
		// Outros
		Ajuda_Padrao.setTexto("Selecione uma consulta pendente e escolha a opção Confirmar ou Cancelar. Ao confirmar será enviada uma confirmação de sua presença a consulta e ao cancelar será realizada o cancelamento da consulta.");
		
		iTelaAtual = TL_PENDENTES;
		
		display.setCurrent(form);
	}
	
	protected void telaConsultasRealizadas()
	{
		form = new Form("Agencel - Consultas Realizadas");
		cmdOkRealizadas = new Command("Ok", Command.OK,1);
	
		form.addCommand(cmdOkRealizadas);
		form.setCommandListener(this);
		
		form.append(sCabecalhoPadrao);
		ajustaHorarioConsultasRealizadas();
		
		display.setCurrent(form);
	}
	
	private void exibeSobre()
	{
		String vMsg="";
		
		vMsg = "Agencel v1.0 beta\nDomicio Rinaldo\nMarcelo Guerra\n\nSalto-2009"; 
			
		ExibeMsgInformacao(vMsg, "Sobre");
	}
	
	private void Ajuda()
	{
		Ajuda_Padrao.show(display);
	}
	
	private void ExibeMsgErro(String pTexto){
		
		Alert alert = new Alert("Erro", pTexto, null, AlertType.ERROR);
		alert.setTimeout(Alert.FOREVER);
		
		alert.setString(pTexto);
		
		display.setCurrent(alert);
	}
	private void ExibeMsgInformacao(String pTexto)
	{
		ExibeMsgInformacao(pTexto, "Info");
	}
	
	private void ExibeMsgInformacao(String pTexto, String pTextoCab)
	{
		Alert alert = new Alert(pTextoCab, pTexto, null, AlertType.INFO);
		alert.setTimeout(Alert.FOREVER);
		
		alert.setString(pTexto);
		
		display.setCurrent(alert);
	}
	
	private void ExibeTelaConfirmacao(String pTexto)
	{
		alertConfirmacao.setTimeout(Alert.FOREVER);
		
		alertConfirmacao.setString(pTexto);

		alertConfirmacao.addCommand(cmdOkConfirmacao);
		alertConfirmacao.addCommand(cmdCancConfirmacao);
		alertConfirmacao.setCommandListener(this);
		
		display.setCurrent(alertConfirmacao);
	}
	
	public boolean AcessoValidado()
	{
		// Opção Novo Paciente
		if ( cgPaciente.getString(cgPaciente.getSelectedIndex()).equals("<Novo>"))
		{
			System.out.println("AcessoValidado - paciente novo");
			
			if (txtCodAcesso.getString().equals(""))
			{
				ExibeMsgErro("Login invalido. Para um novo acesso, deve-se informar o codigo de acesso.");

				return false;
			}
			else 
			{
				System.out.println("Acessovalidado - Antes do ConectarServidor");

				// Buscar as informações do paciente.
				if (ConectarServidor("paciente_serv?comando=PSQACESSO_M&cod_acesso="+txtCodAcesso.getString(), RECEBERDADOS))
				{
					Generico objPaciente = null;
					
					objPaciente = (Generico) vecDados.elementAt(0);
					
					ExibeMsgInformacao("Cod Paciente:"+objPaciente.getCodigo()+"\nNome:"+objPaciente.getNome());
					iCodPacSel = objPaciente.getCodigo();
					
					// Armazenar o paciente no celular para o próximo acesso.
					Registros objReg = new Registros();
					try
					{
						objReg.abrirArmazenador(Registros.REG_PACIENTES);
						if ( !objReg.adicionar(objPaciente) )
						{
							throw new Exception("não foi possivel adicionar registros.");
						}
					}
					catch (Exception ex)
					{
						ExibeMsgErro("Erro ao gravar dados do paciente no celular. Erro:"+ex.getMessage());
					}
					finally 
					{
						objReg.fecharArmazenador();
					}
					
					return true;
				}
				else
				{
					if (vecDados == null)
					{
						ExibeMsgErro("Código de acesso inválido!\nPor favor, verifique o código digitado.");
					}

					return false;
				}
			}
		}
		else
		{
			int iIndexPacSel = -1; 
			
			iIndexPacSel = cgPaciente.getSelectedIndex()-1;
			
			// Obter o código do paciente selecionado.
			if ((vecDados != null) && (iIndexPacSel >= 0))
			{
				Generico objPac = new Generico(); 
				
				objPac = (Generico) vecDados.elementAt(iIndexPacSel);
				
				iCodPacSel = objPac.getCodigo();
			}
			
			return true;
		}
	}
	
	public void carregaPacientesGravadosCel()
	{
		System.out.println("função carregaPacientesGravadosCel");
		
		Registros objReg = new Registros();
		try
		{
			objReg.abrirArmazenador(Registros.REG_PACIENTES);
			System.out.println("antes do listar todos pacientes");
			vecDados = objReg.ListarTodos();
			
			System.out.println("VecDados="+String.valueOf(vecDados));

			ajustaDadosChoiceGroup(cgPaciente, vecDados);
		}
		catch (Exception ex)
		{
			ExibeMsgErro("Erro ao ler dados do paciente armazenados no celular. Erro:"+ex.getMessage());
		}
		finally 
		{
			objReg.fecharArmazenador();
		}

	}
	
	public void ajustaDadosChoiceGroup(ChoiceGroup pCG, Vector pVec) throws Exception
	{
		ajustaDadosChoiceGroup(pCG, pVec, "");
	}
	
	public void ajustaDadosChoiceGroup(ChoiceGroup pCG, Vector pVec, String pTextoInicial) throws Exception
	{
		int i=0;
		Generico objGenerico = null;
		
		for (i=0;i<pVec.size();i++)
		{
			objGenerico = (Generico) pVec.elementAt(i);
			
			pCG.append(pTextoInicial+objGenerico.getNome(), null);
		}
		
	}

	public void ajustaCamposEnvio(int pCampoCod, String pCampoDesc)
	{
			Vector vecDados = new Vector();
			Generico objDadosGenericos = null;
			int i=0;
			String sValorCampo = "";
			
			// Instanciar o objeto generico para o primeiro registro
			objDadosGenericos = new Generico();
		
	}
	
	public boolean ConectarServidor(String pEndProcesso, int pTipoConexao)
	{
		return ConectarServidor(pEndProcesso, pTipoConexao, null);
	}
	
	public boolean ConectarServidor(String pEndProcesso, int pTipoConexao, Consulta_Tab pDadosEnvio)
	{
		boolean bConexaoOk = false;
		
		Con_Servlet objCon_Serv = new Con_Servlet(sEndServidor+pEndProcesso);
		
		try
		{
			bConexaoOk = false;
			System.out.println("Dentro do ConectarServidor");
			
			if (pTipoConexao == objCon_Serv.EXE_ENVIO)
			{
				objCon_Serv.setDadosConsulta(pDadosEnvio);
				System.out.println("Dentro do if de conexao de envio");
			}

			System.out.println("antes da funcao conectar()");
			
			objCon_Serv.Conectar(pTipoConexao);

			System.out.println("DEPOIS da funcao conectar()");
			
			// A cada 1 segundo verifica ainda está conectado ao servidor.
			int iTempo=0;
			while ( objCon_Serv.Conectado() )
			{	
				Thread.sleep(1000);
				
				iTempo++;
				if (iTempo > TIMEOUT)
				{
					vecDados = null;
					throw new Exception("Tempo Esgotado de tentativas de conexão com o servidor. Por favor, tente novamente."); 
				}
					
			}
			
			if (pTipoConexao == objCon_Serv.EXE_RECEB)
			{
				vecDados = objCon_Serv.getDadosGenericos();
			}
			
			if ( objCon_Serv.existeErro() )
			{
				throw new Exception("");
			}
			else
				bConexaoOk = true;
		}
		catch(Exception e) 
		{
			bConexaoOk = false;
			
			if (objCon_Serv.getTipoErro() != objCon_Serv.TIPOERRO_SEMDADOS)
				ExibeMsgErro("Erro durante a conexão com o servidor ("+objCon_Serv.getMensagemErro()+").");
			
			//e.printStackTrace();
			
			vecDados = null;
		}
		
		return bConexaoOk;
	}

	public void ajustaHorarioConsultasRealizadas()
	{
		cgHorarios = new ChoiceGroup("Consultas:",ChoiceGroup.EXCLUSIVE); 
		
		ConectarServidor("consulta_serv?comando=PSQREALIZADAS_M&paciente="+iCodPacSel, RECEBERDADOS);

		if (vecDados != null)
		{
			try
			{
				ajustaDadosChoiceGroup(cgHorarios, vecDados, "Medico: ");
				form.append(cgHorarios);
			}
			catch(Exception e)
			{
				ExibeMsgErro("Erro ao pesquisar consultas realizadas. ("+e.getMessage()+")");
			}
		}
		else
		{
			form.append("Não há nenhuma consulta realizada.");
		}
		
	}
	
	public void ajustaHorarioConsultasPendentes()
	{
		cgHorarios = new ChoiceGroup("Consultas Pendentes:",ChoiceGroup.EXCLUSIVE); 
		
		ConectarServidor("consulta_serv?comando=PSQPENDENTES_M&paciente="+iCodPacSel, RECEBERDADOS);

		// Verificação para tratar tela caso não haja nenhuma consulta pendente.
		//if (true)
		//{
		//	form.append("<temp. fixo> Não há nenhuma consulta pendente.");
		//}
		/*
		else
		{ addComand cmdOkPendente / cmdCancPendente 
		*/
		
		if (vecDados != null)
		{
			try
			{
				ajustaDadosChoiceGroup(cgHorarios, vecDados, "Medico: ");
				form.append(cgHorarios);
			}
			catch(Exception e)
			{
				ExibeMsgErro("Erro ao pesquisar consultas pendentes. ("+e.getMessage()+")");
			}
		}
		else
		{
			form.append("Não há nenhuma consulta pendente.");
		}
		
	}

	
	public void ajustaEspecialidadesMarcarConsulta()
	{
		// Especialidades //
		ConectarServidor("especialidade_serv?comando=LST_M", RECEBERDADOS);

		if (vecDados != null)
		{
			try
			{
				ajustaDadosChoiceGroup(cgEspec, vecDados);
			}
			catch(Exception e)
			{}
		}	
	}
	
	public void ajustaMedicosMarcarConsulta(int pIndexEspecSel)
	{
		if (iTelaAtual == TL_MARCAR)
		{
			int iCodEspecSel = -1;
			System.out.println("\nTODAS 2");

			if (vecDados != null)
			{
				Generico objEspec = new Generico();
				
				System.out.println("\nTODAS 3 - pIndexEspecSel="+ pIndexEspecSel);
				// Pegar os dados da especialidade selecionada.
				if (pIndexEspecSel >= 0)
				{
					objEspec = (Generico) vecDados.elementAt(pIndexEspecSel);
					iCodEspecSel = objEspec.getCodigo();
				}
				else 
				{
					if (pIndexEspecSel == -1)
						iCodEspecSel = 0;
				}
				System.out.println("\nTODAS 4 - iCodEspecSel="+ iCodEspecSel);
				
				// Preparar o Choice Group do Medico
				cgMedico.deleteAll();
				cgMedico.append(CG_SELECIONE, null);
				
				if (iCodEspecSel >= 0)
				{
					vecDados = new Vector();

					// Pesquisar Médicos da especialidade.
					ConectarServidor("medico_serv?comando=PSQ_M&cod_esp="+iCodEspecSel, RECEBERDADOS);

					try
					{
						if (vecDados != null)
							ajustaDadosChoiceGroup(cgMedico, vecDados);
						
						form.append(cgMedico);
					}
					catch(Exception e) 
					{
						System.out.print("Erro ao receber dados. Erro("+e.getMessage()+")");
					}
				}
			}
			
			System.out.println("Metodo ajustaMedicosMarcarConsulta reformulado");
		}
	}
	
	public void ajustaAnoDispMarcarConsulta()
	{
		
		if (iTelaAtual == TL_MARCAR && iCodMedSel > 0)
		{
			System.out.println("\n metodo ajustaAnoDispMarcarConsulta");
			
			// Objetos de data e hora
			Date dtAtual = new Date();
			Calendar calDataDisp = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
			calDataDisp.setTime(dtAtual);
			
			int iMes = calDataDisp.get(Calendar.MONTH)+1;
			int iAno = calDataDisp.get(Calendar.YEAR); 

			cgAno.append(String.valueOf(iAno), null);

			//Ajustar o ano
			int i=0;
			
			for (i=0;i<iMesesApos;i++)
			{
				if (iMes == 12)
				{
					iMes = 1;
					iAno++;
					cgAno.append(String.valueOf(iAno), null);
				}
				else
					iMes++;
			}
			
			form.append("Datas Disponives");
			form.append(cgAno);
		}
			
	}
	
	public void ajustaMesDispMarcarConsulta()
	{
		if (iTelaAtual == TL_MARCAR && iCodMedSel > 0)
		{
			System.out.println("\n metodo ajustaMesDispMarcarConsulta");

			// Objetos de data e hora
			Date dtAtual = new Date();
			Calendar calDataDisp = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
			calDataDisp.setTime(dtAtual);
			
			int iMes = calDataDisp.get(Calendar.MONTH)+1;
			int iAno = calDataDisp.get(Calendar.YEAR); 

			cgMes.deleteAll();

			// Ajustar o mes
			int i=0;
			String sAnoSel = cgAno.getString(cgAno.getSelectedIndex());
			
			for (i=0;i<iMesesApos;i++)
			{
				// Se ano corrente for o mesmo do ano atual 
				if (sAnoSel.equals(String.valueOf(iAno)))
				{
					cgMes.append(String.valueOf(iMes), null);
				}
				
				if (iMes == 12)
				{
					iMes = 1;
					iAno++;
				}
				else
					iMes++;
			}
			
			form.append(cgMes);
		}
	}
	
	public void ajustaDiasDispMarcarConsulta()
	{
		if (iTelaAtual == TL_MARCAR && iCodMedSel > 0)
		{
			System.out.println("\n metodo ajustaMesDispMarcarConsulta");
			
			// Juntar os mês e ano para solicitar 
			String sMesAno="";
			
			sMesAno =  "01-"+cgMes.getString(cgMes.getSelectedIndex())+"-";
			sMesAno += cgAno.getString(cgAno.getSelectedIndex());
			
			ConectarServidor("consulta_serv?comando=PSQDIAS_M&datapsq="+sMesAno+"&medico="+iCodMedSel, RECEBERDADOS);

			try
			{
				if (vecDados != null)
					ajustaDadosChoiceGroup(cgDia, vecDados);
				
				form.append(cgDia);
			}
			catch(Exception e) 
			{
				System.out.print("Erro ao receber dados. Erro("+e.getMessage()+")");
			}
		}
		
	}
	
	public void ajustaHorariosDispMarcarConsulta()
	{
		//
		if (iTelaAtual == TL_MARCAR && iCodMedSel > 0)
		{
			String sDataPesquisa="";
			
			sDataPesquisa =  cgDia.getString(cgDia.getSelectedIndex())+"-";
			sDataPesquisa += cgMes.getString(cgMes.getSelectedIndex())+"-";
			sDataPesquisa += cgAno.getString(cgAno.getSelectedIndex());
			
			ConectarServidor("consulta_serv?comando=PSQHORA_M&datapsq="+sDataPesquisa+"&medico="+iCodMedSel, RECEBERDADOS);

			try
			{
				if (vecDados != null)
				{
					ajustaDadosChoiceGroup(cgHorarios, vecDados);
				}
				
				form.append(cgHorarios);
			}
			catch(Exception e) 
			{
				System.out.print("Erro ao receber dados. Erro("+e.getMessage()+")");
			}
		}
	}

	public void confirmarMarcarConsulta()
	{
		// Enviar apenas o código da agenda e o paciente que deverá ser recebido junto do horário pra marcar.
		String sConfirmaCons = "";
		sConfirmaCons =  "Confirme os dados para consulta:";
		sConfirmaCons += "\nMedico: "+cgMedico.getString(cgMedico.getSelectedIndex());
		sConfirmaCons += "\nDia: "+cgDia.getString(cgDia.getSelectedIndex())+"/"+cgMes.getString(cgMes.getSelectedIndex())+"/"+cgAno.getString(cgAno.getSelectedIndex());
		sConfirmaCons += "\nHora: "+cgHorarios.getString(cgHorarios.getSelectedIndex());

		ExibeTelaConfirmacao(sConfirmaCons);
	}
	
	public void confirmarConfirmacaoConsulta()
	{
		// Enviar apenas o código da agenda e o paciente que deverá ser recebido junto do horário pra confirmar.
		String sConfirmaCons = "";
		sConfirmaCons =  "Confirme o envio da confirmacao da consulta:";
		sConfirmaCons += "\n"+cgHorarios.getString(cgHorarios.getSelectedIndex());

		ExibeTelaConfirmacao(sConfirmaCons);
	}
	
	public void confirmarCancelamentoConsulta()
	{
		// Enviar apenas o código da agenda e o paciente que deverá ser recebido junto do horário pra cancelar.
		String sConfirmaCons = "";
		sConfirmaCons =  "Confirme o envio de cancelamento da consulta:";
		sConfirmaCons += "\n"+cgHorarios.getString(cgHorarios.getSelectedIndex());

		ExibeTelaConfirmacao(sConfirmaCons);
	}

	// Métodos do tipo Processos enviam dados ao banco.
	public void processoMarcarConsulta()
	{
		formMsg.setString("Por favor, aguarde...");
		
		display.setCurrent(formMsg);
		
		// Obter o código da agenda selecionada
		int iCodAgendaSel=0;
		
		if (vecDados != null)
		{
			Generico objHorarios = new Generico();
			
			// Pegar os dados do horário selecionado.
			objHorarios = (Generico) vecDados.elementAt(cgHorarios.getSelectedIndex());
			iCodAgendaSel = objHorarios.getCodigo();
		}

		formMsg.setString(formMsg.getString()+"\n AGE_IN_CODIGO="+iCodAgendaSel);
		
		// Preparar dados para envio
		Consulta_Tab objMarcarConsulta = new Consulta_Tab();
		objMarcarConsulta.setAgendaCodigo(iCodAgendaSel);
		objMarcarConsulta.setPacienteCodigo(iCodPacSel);
		
		// Enviar dados para marcar a consulta //
		if (ConectarServidor("consulta_serv?comando=GRAVAR_M", ENVIARDADOS, objMarcarConsulta))
		{
			formMsg.setString("Consulta agendada com sucesso!");
			formMsg.addCommand(cmdOkMensagem);
		}
		else
		{
			formMsg.setString("Não foi possivel agendar a consulta. Clique na opção Ok para tentar novamente ou clique na opção Cancelar para cancelar a operação e ir ao menu principal.");
			formMsg.addCommand(cmdCancMensagem);
			formMsg.addCommand(cmdTentarMensagem);
		}
		formMsg.setCommandListener(this);
		
		System.out.println("Ultima linha do processo processoMarcarConsulta()");
	}
	
	public void processoConfirmacaoConsulta()
	{
		formMsg.setString("Por favor, aguarde...");
		
		display.setCurrent(formMsg);
		
		// Obter o código da agenda selecionada
		int iCodAgendaSel=0;
		
		if (vecDados != null)
		{
			Generico objHorarios = new Generico();
			
			// Pegar os dados do horário selecionado.
			objHorarios = (Generico) vecDados.elementAt(cgHorarios.getSelectedIndex());
			iCodAgendaSel = objHorarios.getCodigo();
		}

		// Preparar dados para envio
		Consulta_Tab objConfirmarConsulta = new Consulta_Tab();
		objConfirmarConsulta.setAgendaCodigo(iCodAgendaSel);
		objConfirmarConsulta.setPacienteCodigo(iCodPacSel);
		
		// Enviar dados para confirmar a consulta //
		if (ConectarServidor("consulta_serv?comando=CONFIRMAR_M", ENVIARDADOS, objConfirmarConsulta))
		{
			formMsg.setString("Consulta confirmada com sucesso!");
			formMsg.addCommand(cmdOkMensagem);
		}
		else
		{
			formMsg.setString("Não foi possivel confirmar a consulta. Clique na opção Ok para tentar novamente ou clique na opção Cancelar para cancelar a operação e ir ao menu principal.");
			formMsg.addCommand(cmdCancMensagem);
			formMsg.addCommand(cmdTentarMensagem);
		}
		formMsg.setCommandListener(this);
	}
	
	public void processoCancelarConsulta()
	{
		formMsg.setString("Por favor, aguarde...");
		
		display.setCurrent(formMsg);
		
		// Obter o código da agenda selecionada
		int iCodAgendaSel=0;
		
		if (vecDados != null)
		{
			Generico objHorarios = new Generico();
			
			// Pegar os dados do horário selecionado.
			objHorarios = (Generico) vecDados.elementAt(cgHorarios.getSelectedIndex());
			iCodAgendaSel = objHorarios.getCodigo();
		}

		formMsg.setString(formMsg.getString()+"\n AGE_IN_CODIGO="+iCodAgendaSel);
		
		// Preparar dados para envio
		Consulta_Tab objCancelarConsulta = new Consulta_Tab();
		objCancelarConsulta.setAgendaCodigo(iCodAgendaSel);
		objCancelarConsulta.setPacienteCodigo(iCodPacSel);
		
		// Enviar dados para confirmar a consulta //
		if (ConectarServidor("consulta_serv?comando=CANCELAR_M", ENVIARDADOS, objCancelarConsulta))
		{
			formMsg.setString("Consulta cancelada com sucesso!");
			formMsg.addCommand(cmdOkMensagem);
		}
		else
		{
			formMsg.setString("Não foi possivel cancelar a consulta. Clique na opção Ok para tentar novamente ou clique na opção Cancelar para cancelar a operação e ir ao menu principal.");
			formMsg.addCommand(cmdCancMensagem);
			formMsg.addCommand(cmdTentarMensagem);
		}
		formMsg.setCommandListener(this);
	}
	
	// Metodos do tipo AjustaSelecao são chamados por eventos.
	public void AjustaSelecaoPaciente(Choice pChoice)
	{
		System.out.println("Paciente Sel:"+pChoice.getString(pChoice.getSelectedIndex()));
		
		if (pChoice.getString(pChoice.getSelectedIndex()).equals("<Novo>"))
		{
			txtCodAcesso.setConstraints(TextField.ANY);
		}
		else
		{	
			txtCodAcesso.setConstraints(TextField.UNEDITABLE);
			txtCodAcesso.setString("");
		}
	}

	public void AjustaSelecaoEspec(Choice pChoice)
	{
		int iIndexEspecSel = -1;
		
		if (pChoice.getString(pChoice.getSelectedIndex()).equals(CG_TODAS))
		{
			iIndexEspecSel = -1;
		}
		else if (pChoice.getString(pChoice.getSelectedIndex()).equals(CG_SELECIONE))
		{
			iIndexEspecSel = -2;
		}
		else
		{	
			iIndexEspecSel = pChoice.getSelectedIndex()-1;
		}
		
		ajustaMedicosMarcarConsulta(iIndexEspecSel);
	}
	
	public void AjustaSelecaoMedicos(Choice pChoice)
	{
		int iIndexMedSel=-1;
		
		// Ajustar o indice pra poder obter o código.
		if (pChoice.getString(pChoice.getSelectedIndex()).equals(CG_SELECIONE))
		{
			iIndexMedSel = -1;
		}
		else
		{	
			iIndexMedSel = pChoice.getSelectedIndex()-1;
		}
		
		// Obter o codigo do médico selecionado.
		if ((vecDados != null) && (iIndexMedSel >= 0))
		{
			Generico objMedico = new Generico();
			
			System.out.println("\n Obter codigo Medico selecionado. ");

			// Pegar os dados do medico selecionado.
			objMedico = (Generico) vecDados.elementAt(iIndexMedSel);
			iCodMedSel = objMedico.getCodigo();

			ajustaAnoDispMarcarConsulta();
		}
	}

	// Eventos //
	public void commandAction(Command cmd, Displayable dsp)
	{
		if (cmd == cmdAjuda)
		{
			Ajuda();
		}
		else if (cmd == cmdSair)
		{
			destroyApp(true);
		}
		else if (cmd == cmdVoltar)
		{
			if (iTelaAtual == TL_MARCAR || iTelaAtual == TL_VISUALIZAR){
				telaMenuPaciente();
			}
			else if (iTelaAtual == TL_PENDENTES){
				telaSubMenuVisualizar();
			}
		}
		else if (cmd == cmdOkAcessoPac)
		{
			if ( AcessoValidado() ) 
			{
				telaMenuPaciente();
			}
		}
		else if (cmd == cmdOkRealizadas)
		{
			telaMenuPaciente();
		}
		else if (cmd == cmdOkMarcar)
		{
			confirmarMarcarConsulta();
		}
		else if (cmd == cmdOkPendente)
		{
			confirmarConfirmacaoConsulta();
			iProcessoAtual = PC_OK;
		}
		else if (cmd == cmdCancPendente)
		{
			confirmarCancelamentoConsulta();
			iProcessoAtual = PC_CANCELAR;
		}
		else if (cmd == cmdOkConfirmacao)
		{
			if (iTelaAtual == TL_MARCAR)
			{
				processoMarcarConsulta();
			}
			else if (iTelaAtual == TL_PENDENTES)
			{
				if (iProcessoAtual == PC_OK)
					processoConfirmacaoConsulta();
				else if (iProcessoAtual == PC_CANCELAR)
					processoCancelarConsulta();
			}
		}
		else if (cmd == cmdCancConfirmacao)
		{
			if ((iTelaAtual == TL_MARCAR) || (iTelaAtual == TL_PENDENTES) ) 
			{
				display.setCurrent(form);
			}
		}
		else if (cmd == cmdCancMensagem)
		{
			telaMenuPaciente();
		}
		else if (cmd == cmdOkMensagem)
		{
			telaMenuPaciente();
		}
		else if (cmd == cmdTentarMensagem)
		{
			if (iTelaAtual == TL_MARCAR)
			{
				confirmarMarcarConsulta();
			}
		}
		
		//Seleção dos Forms do tipo List
		if (cmd == List.SELECT_COMMAND)
		{
			String sOpcaoSel = formlist.getString(formlist.getSelectedIndex());
			
			if (sOpcaoSel == MN_MARCAR)
			{
				telaMarcarConsulta();
			}
			else if (sOpcaoSel == MN_VISUALIZAR)
			{
				telaSubMenuVisualizar();
			}
			else if (sOpcaoSel == MN_PENDENTES)
			{
				telaConsultasPendentes();
			}
			else if (sOpcaoSel == MN_REALIZADAS)
			{
				telaConsultasRealizadas();
			}
			else if (sOpcaoSel == MN_SOBRE)
			{
				exibeSobre();
			}
		}
	}

	public void itemStateChanged(Item item) 
	{
		// Itens do tipo Choice Group.
		if (item instanceof ChoiceGroup)
		{
			if (item.getLabel() == cgPaciente.getLabel())
			{
				AjustaSelecaoPaciente((Choice)item);
			}
			else if (item.getLabel() == cgEspec.getLabel())
			{
				AjustaSelecaoEspec((Choice)item);
			}
			else if (item.getLabel() == cgMedico.getLabel())
			{
				AjustaSelecaoMedicos((Choice)item);
			}
			else if (item.getLabel() == cgAno.getLabel())
			{
				ajustaMesDispMarcarConsulta();
			}
			else if (item.getLabel() == cgMes.getLabel())
			{
				ajustaDiasDispMarcarConsulta();
			}
			else if (item.getLabel() == cgDia.getLabel())
			{
				ajustaHorariosDispMarcarConsulta();
			}
			
		}
		
		System.out.println("itemStateChanged -> item:"+item.getLabel());
	}

}
