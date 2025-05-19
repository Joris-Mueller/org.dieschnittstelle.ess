package org.dieschnittstelle.ess.jrs;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import java.util.List;

/*
 * JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	@Context
	private ServletContext servletContext;

	private GenericCRUDExecutor<AbstractProduct> getExecFromServletContext(){
		return (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
	}

	@Override
	public AbstractProduct createProduct(
			AbstractProduct prod) {
		return (AbstractProduct) getExecFromServletContext().createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts() {
		return (List<AbstractProduct>) getExecFromServletContext().readAllObjects();
	}

	@Override
	public AbstractProduct updateProduct(long id, AbstractProduct update) {
		return (AbstractProduct) getExecFromServletContext().updateObject(update);
	}

	@Override
	public boolean deleteProduct(long id) {
		return getExecFromServletContext().deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
		AbstractProduct pd = (AbstractProduct) getExecFromServletContext().readObject(id);
		if(pd != null){
			return pd;
		} else {
			throw new NotFoundException("The product with id " + id + " does not exist!");
		}
	}
	
}
