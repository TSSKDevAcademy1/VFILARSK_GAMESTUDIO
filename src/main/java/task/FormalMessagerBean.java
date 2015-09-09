package task;

import javax.enterprise.inject.Default;

@Default
public class FormalMessagerBean implements Messager {
public String getMessage(){
	return "Vlado ta zdravi.";
}
}
