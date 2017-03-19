package sample.demo.netty.data.service.impl.decoder;


import org.junit.Assert;
import org.junit.Test;
import sample.demo.netty.utils.PatternBuilder;

public class PatternBuilderTest {

    @Test
    public void testPatternBuilder() {
        Assert.assertEquals("\\$GPRMC", new PatternBuilder().text("$GPRMC").toString());
        Assert.assertEquals("(\\d{2}\\.[0-9a-fA-F]+)", new PatternBuilder().number("(dd.x+)").toString());
        Assert.assertEquals("a(?:bc)?", new PatternBuilder().text("a").text("b").text("c").optional(2).toString());
        Assert.assertEquals("a|b", new PatternBuilder().expression("a|b").toString());
        Assert.assertEquals("ab\\|", new PatternBuilder().expression("ab|").toString());
        Assert.assertEquals("|", new PatternBuilder().or().toString());
        Assert.assertEquals("\\|\\d|\\d\\|", new PatternBuilder().number("|d|d|").toString());
    }
}




