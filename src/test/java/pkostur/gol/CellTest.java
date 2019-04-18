package pkostur.gol;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class CellTest {


    @Test
    public void shouldBeAbleToCreateCell() {
        Assert.assertThat(Cell.at(0, 0), Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void cellShouldBeEqual() {
        Assert.assertThat(Cell.at(0, 0), Matchers.equalTo(Cell.at(0, 0)));
        Assert.assertThat(Cell.at(3, 5), Matchers.equalTo(Cell.at(3, 5)));
    }
}
