package com.ajax.restapiproject.user.model;

import org.springframework.stereotype.Component;

import com.ajax.restapiproject.country.model.Country;
import com.ajax.restapiproject.document.model.Document;
import com.ajax.restapiproject.office.model.Office;
import com.ajax.restapiproject.user.view.UserListViewReq;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * Mapping of user request to a user object
 * @author Al
 *
 */
@Component
public class UserMapper extends ConfigurableMapper{
			
	protected void configure (MapperFactory factory) {
		
		factory.classMap(UserListViewReq.class, User.class)
			.customize(new CustomMapper<UserListViewReq, User>(){
				@Override
				public void mapAtoB(UserListViewReq a, User b, MappingContext context) {
					
					Office office = (Office) context.getProperty("office");
					
					Document doc = (Document) context.getProperty("doc");
					
					Country country = (Country) context.getProperty("country");
					
					if (office != null) {
						b.setOffice(office);
					}
					
					if (doc != null) {
						b.setDoc(doc);
					}
					
					if (country != null) {
						b.setCountry(country);
					}
				}
			})
			.byDefault()
			.register();
	}
}
