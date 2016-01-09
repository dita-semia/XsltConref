/*
 * This file is part of the DITA-SEMIA project (www.dita-semia.org).
 * See the accompanying LICENSE file for applicable licenses.
 */

package org.DitaSemia.Oxygen.XsltConref;

import javax.xml.transform.sax.SAXSource;

import org.DitaSemia.Base.XsltConref.XsltConref;
import org.DitaSemia.Oxygen.AuthorNodeWrapper;
import org.apache.log4j.Logger;
import org.xml.sax.EntityResolver;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.ReferenceResolverException;
import ro.sync.ecss.extensions.api.ValidatingReferenceResolverException;
import ro.sync.ecss.extensions.api.node.AuthorDocument;
import ro.sync.ecss.extensions.api.node.AuthorNode;
import ro.sync.ecss.extensions.dita.conref.DITAConRefResolver;

public class DitaSemiaReferenceResolver extends DITAConRefResolver {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(DitaSemiaReferenceResolver.class.getName());
	
	@Override
	public String getDescription()  {
		return "Resolves the 'xslt-conref/conkeyref/conref/keyref' references";
	}

	@Override
	public String getDisplayName(AuthorNode node) {
		String displayName = null;
		final XsltConref xsltConref = XsltConrefResolver.xsltConrefFromNode(node, null);
		if (xsltConref != null) {
			displayName = xsltConref.getScriptUrl().toString();
		} else {
			displayName = super.getDisplayName(node);
		}
		//logger.info("getDisplayName: " + displayName);
		return displayName;
	}

	@Override
	public String getReferenceSystemID(AuthorNode node, AuthorAccess authorAccess) {
		String systemID = null;
		if (XsltConref.isXsltConref(new AuthorNodeWrapper(node, null))) {
			systemID = node.getXMLBaseURL().toExternalForm();
		} else {
			systemID = super.getReferenceSystemID(node, authorAccess);
		}
		//logger.info("getReferenceSystemID: " + systemID);
		return systemID;
	}

	@Override
	public String getReferenceUniqueID(AuthorNode node) {
		String referenceUniqueID = null;
		final XsltConref xsltConref = XsltConrefResolver.xsltConrefFromNode(node, null);
		if (xsltConref != null) {
			referenceUniqueID = xsltConref.getScriptUrlAsString().toString();
		} else {
			referenceUniqueID = super.getReferenceUniqueID(node);
		}
		//logger.info("getReferenceUniqueID: " + referenceUniqueID);
		return referenceUniqueID;	
	}

	@Override
	public boolean hasReferences(AuthorNode node) {
		boolean hasReferences = false;
		if (XsltConref.isXsltConref(new AuthorNodeWrapper(node, null))) {
			hasReferences = true;
		} else {
			hasReferences = super.hasReferences(node);
		}
		//logger.info("hasReferences: " + hasReferences);
		return hasReferences;
	}

	@Override
	public boolean isReferenceChanged(AuthorNode node, String attributeName) {
		//logger.info("isReferenceChanged");
		boolean isChanged = false;
		if (XsltConref.isXsltConref(new AuthorNodeWrapper(node, null))) {
			return XsltConrefResolver.isXsltConrefAttr(node, attributeName);
		} else {
			isChanged = super.isReferenceChanged(node, attributeName);
		}
		return isChanged;
	}
	
	@Override
	public void checkTarget(AuthorNode node, AuthorDocument targetDocument) throws ValidatingReferenceResolverException {
		if (XsltConref.isXsltConref(new AuthorNodeWrapper(node, null))) {
			XsltConrefResolver.checkXsltConrefTarget(node, targetDocument);	
		} else {
			super.checkTarget(node, targetDocument);
		}
	}
	
	@Override
	public SAXSource resolveReference(AuthorNode node, String systemID, AuthorAccess authorAccess, EntityResolver entityResolver) throws ReferenceResolverException {
		final XsltConref 	xsltConref 	= XsltConrefResolver.xsltConrefFromNode(node, authorAccess);
		SAXSource 			saxSource 	= null;
		if (xsltConref != null) {
			saxSource = XsltConrefResolver.resolveXsltConref(xsltConref, authorAccess);
		} else {
			saxSource = super.resolveReference(node, systemID, authorAccess, entityResolver);
		}
		return saxSource;
	}

}
