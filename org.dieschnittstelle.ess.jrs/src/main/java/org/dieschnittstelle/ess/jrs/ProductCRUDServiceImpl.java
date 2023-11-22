package org.dieschnittstelle.ess.jrs;

import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import java.util.List;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	@Override
	public IndividualisedProductItem createProduct(
			IndividualisedProductItem prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IndividualisedProductItem> readAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndividualisedProductItem updateProduct(long id,
			IndividualisedProductItem update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IndividualisedProductItem readProduct(long id) {
		GenericCRUDExecutor exec = (GenericCRUDExecutor) getSer
		return null;
	}
	
}
