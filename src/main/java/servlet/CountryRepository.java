package servlet;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped
public class CountryRepository {
	private List<String> countries = new ArrayList<>(); 
	Locale[] locales = Locale.getAvailableLocales();
	
	public List<String> getCountries(){
		for(Locale country : locales){
			countries.add(country.getDisplayCountry());
		}
		return countries;
	}
	
}
