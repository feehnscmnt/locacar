package br.com.locacar.action.email;

import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.*;
import javax.mail.*;
import java.text.*;
import java.util.*;

/**
 * Classe responsável pelo envio automático do e-mail com o arquivo da OS ao cliente após a realização da locação!
 * @author Felipe Nascimento
 */
public class EnviarEmail {
	private static final Logger LOG = LogManager.getLogger(EnviarEmail.class.getName());
	private static MimeBodyPart mbp;
	private static StringBuilder sb;
	
	public static void enviarEmail(String emailDest, String numeroLocacao) {
		try {
			LOG.info("Enviando e-mail...");
			ConfigEmailUtil.carregarConfig();
			Properties props = new Properties();
			props.put("mail.smtp.host", ConfigEmailUtil.mail_smtp_host);
			props.put("mail.smtp.socketFactory.port", ConfigEmailUtil.mail_smtp_socketFactory_port);
			props.put("mail.smtp.socketFactory.class", ConfigEmailUtil.mail_smtp_socketFactory_class);
			props.put("mail.smtp.auth", ConfigEmailUtil.mail_smtp_auth);
			props.put("mail.smtp.port", ConfigEmailUtil.mail_smtp_port);
			
			//Cria a sessão!
			Session sessao = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(ConfigEmailUtil.mail_user, ConfigEmailUtil.mail_password);
				}
			});
			Message mensagem = new MimeMessage(sessao);
			mensagem.setFrom(new InternetAddress(ConfigEmailUtil.mail_user));
			Address destinatario[] = InternetAddress.parse(emailDest.toLowerCase());
			mensagem.setRecipients(Message.RecipientType.TO, destinatario);
			mensagem.setSubject(assuntoEmail());
			Multipart multipart = new MimeMultipart();
			
			//Cria a mensagem!
			mbp = new MimeBodyPart();
			mbp.setContent(corpoEmail(numeroLocacao), "text/html; charset=utf-8");
			multipart.addBodyPart(mbp);
			
			//Cria o anexo!
			Date data = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			FileDataSource anexo = new FileDataSource(PathFilesUtil.getPathOs().concat(sdf.format(data)).concat("/").concat(numeroLocacao).concat(".pdf"));
			mbp = new MimeBodyPart();
			mbp.setDataHandler(new DataHandler(anexo));
			mbp.setFileName(anexo.getName());
			multipart.addBodyPart(mbp);
			
			//Envia o e-mail!
			mensagem.setContent(multipart);
			Transport.send(mensagem);
			LOG.info("E-mail enviado com sucesso!");
		} catch(MessagingException e) {
			LOG.error("Houve falha no envio automático do e-mail para o cliente! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("emailNaoEnviado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private static String assuntoEmail() {
		return "LOCACAR - Notificação de Locação";
	}
	
	private static String corpoEmail(String numeroLocacao) {
		sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html><head>");
		sb.append("<title>Notificação de Locação</title>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		sb.append("</head>");
		sb.append("<body leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\" style=\"font-family: Calibri; font-size: 14pt; color: #505050\">");
		sb.append("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" valign=\"top\" align=\"center\" style=\border: 1px solid #505050\">");
		sb.append("<tr><td>");
		sb.append("<table border=\"0\" style=\"width: 100%; border-collapse: collapse\">");
		sb.append("<tr style=\"margin: 10px\">");
		sb.append("<td style=\"padding: 30px; vertical-align: top;\"> <img src=\"https://i.ibb.co/m9M9GvZ/Abstract-wavy-stylish-banner-design-template-vector.jpg\" alt=\"\">");
		sb.append("<div style=\"font-size: 11px; margin-right: 18px; float: right;\"/>");
		sb.append("</td></tr>");
		sb.append("</table>");
		sb.append("<div style=\"font-family: Calibri; font-size: 14px; color: #505050; margin-left: 28px; width: 597px; word-wrap: break-word;\">Prezado, segue anexo o arquivo referente à locação " + numeroLocacao + ". A mesma se encontra registrada em nosso sistema!<br/><br/></br>Mensagem gerada automaticamente. Por favor, não responda este e-mail!");
		sb.append("</div></br>");
		sb.append("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" valign=\"top\" align=\"center\">");
		sb.append("<tr>");
		sb.append("<td style=\"width: 4%\"/>");
		sb.append("<td style=\"width: 92%\">");
		sb.append("<br/></td>");
		sb.append("<td style=\"width: 4%\"/></tr>");
		sb.append("<tr>");
		sb.append("<td colspan=\"3\" style=\"width: 100%\" align=\"center\"> <img src=\"https://i.ibb.co/0tW5Wfn/Abstract-wavy-stylish-banner-design-template-vector.jpg\" alt=\"\" style=\"width: 100%\" align=\"center\"></td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</td></tr>");
		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");
		return String.valueOf(sb);
	}
}