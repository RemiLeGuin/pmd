/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.ast;

import static net.sourceforge.pmd.lang.apex.ast.ApexParserTestHelpers.parse;

import org.junit.Assert;
import org.junit.Test;

import apex.jorje.semantic.ast.compilation.Compilation;

public class ASTUserInterfaceTest {

    @Test
    public void testInterfaceName() {
        ApexNode<Compilation> node = parse("interface Foo { }");
        Assert.assertSame(ASTUserInterface.class, node.getClass());
        Assert.assertEquals("Foo", node.getImage());
    }

    @Test
    public void testInnerInterfaceName() {
        ApexNode<Compilation> node = parse("class Foo { interface Bar { } }");
        Assert.assertSame(ASTUserClass.class, node.getClass());
        ASTUserInterface innerNode = node.getFirstDescendantOfType(ASTUserInterface.class);
        Assert.assertNotNull(innerNode);
        Assert.assertEquals("Bar", innerNode.getImage());
    }
}
