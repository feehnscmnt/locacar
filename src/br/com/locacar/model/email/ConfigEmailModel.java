package br.com.locacar.model.email;

import javax.xml.bind.annotation.*;

/**
 * Classe list para obtenção das configurações dos e-mails!
 * @author Felipe Nascimento
 */
@XmlRootElement(name="configuracaoGmail")
public class ConfigEmailModel {
	private String mail_smtp_host, mail_smtp_socketFactory_port, mail_smtp_socketFactory_class, mail_smtp_auth, mail_smtp_port, mail_user, mail_password;
	
	@XmlElement(name="mail_smtp_host")
	public String getMail_smtp_host() {
		return mail_smtp_host;
	}

	public void setMail_smtp_host(String mail_smtp_host) {
		this.mail_smtp_host = mail_smtp_host;
	}
	
	@XmlElement(name="mail_smtp_socketFactory_port")
	public String getMail_smtp_socketFactory_port() {
		return mail_smtp_socketFactory_port;
	}

	public void setMail_smtp_socketFactory_port(String mail_smtp_socketFactory_port) {
		this.mail_smtp_socketFactory_port = mail_smtp_socketFactory_port;
	}
	
	@XmlElement(name="mail_smtp_socketFactory_class")
	public String getMail_smtp_socketFactory_class() {
		return mail_smtp_socketFactory_class;
	}

	public void setMail_smtp_socketFactory_class(String mail_smtp_socketFactory_class) {
		this.mail_smtp_socketFactory_class = mail_smtp_socketFactory_class;
	}
	
	@XmlElement(name="mail_smtp_auth")
	public String getMail_smtp_auth() {
		return mail_smtp_auth;
	}

	public void setMail_smtp_auth(String mail_smtp_auth) {
		this.mail_smtp_auth = mail_smtp_auth;
	}
	
	@XmlElement(name="mail_smtp_port")
	public String getMail_smtp_port() {
		return mail_smtp_port;
	}

	public void setMail_smtp_port(String mail_smtp_port) {
		this.mail_smtp_port = mail_smtp_port;
	}
	
	@XmlElement(name="mail_user")
	public String getMail_user() {
		return mail_user;
	}

	public void setMail_user(String mail_user) {
		this.mail_user = mail_user;
	}
	
	@XmlElement(name="mail_password")
	public String getMail_password() {
		return mail_password;
	}

	public void setMail_password(String mail_password) {
		this.mail_password = mail_password;
	}
}