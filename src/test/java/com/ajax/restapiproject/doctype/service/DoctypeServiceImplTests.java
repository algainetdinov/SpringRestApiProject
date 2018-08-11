package com.ajax.restapiproject.doctype.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ajax.restapiproject.doctype.dao.DoctypeDao;
import com.ajax.restapiproject.doctype.model.Doctype;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Tests Doctype service implementation
 * @author Al
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DoctypeServiceImplTests {
	
	@InjectMocks
	private DoctypeServiceImpl doctypeService;
	
	@Mock
	private DoctypeDao doctypeDao;
	
	@Mock
	private Doctype type;
	
	/**
	 * Check that doctype with id 1L exists
	 */
	@Test
	public void loadByIdTest() {	
		
		when(doctypeDao.findById(anyLong())).thenReturn(type);
		assertThat(doctypeService.loadById(1L)).isNotNull();
	}	
	
	/**
	 * Check that list of doctypes could be retrieved
	 */
	@Test
	public void loadAllTest() {
		
		when(doctypeDao.findAll()).thenReturn(Arrays.asList(new Doctype()));
		assertThat(doctypeService.loadAll()).hasAtLeastOneElementOfType(DictionaryView.class);
	}
}
