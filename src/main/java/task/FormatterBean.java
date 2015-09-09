package task;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FormatterBean {
	
	@Inject @Informal Messager messager;
	
	public String getFormattedMessage(){
		return messager.getMessage();
	}
}
