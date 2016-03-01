<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl	= "http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi	= "http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xs	= "http://www.w3.org/2001/XMLSchema"
    xmlns:xcr	= "http://www.dita-semia.org/xslt-conref"
    exclude-result-prefixes="#all">

    <xsl:template match="/">
    	
    	<xsl:processing-instruction name="xml-model">href="urn:oasis:names:tc:dita:rng:topic.rng" schematypens="http://relaxng.org/ns/structure/1.0"</xsl:processing-instruction>
    	
    	<section xcr:reparse="yes">
    		<title>By assigning an RNG schema</title>
    		<p>Just add <codeph>&lt;?xml-model href="urn:oasis:names:tc:dita:rng:topic.rng" schematypens="http://relaxng.org/ns/structure/1.0"?&gt;</codeph> result document.</p>
    		<p>Additionally you should set the attribute <codeph>xcr:reparse</codeph> to "yes".
    			This will ensure that the result is converted to a string first and be parsed again adding the default attributes.
    			Otherwise the dita-ot-resolver might skip this step.</p>
        </section>
    	
    </xsl:template>

</xsl:stylesheet>
