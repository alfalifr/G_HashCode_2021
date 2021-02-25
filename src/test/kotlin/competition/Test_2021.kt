package competition

import compe.util.Util
import org.junit.jupiter.api.Test
import sidev.lib.console.prin

class Test_2021 {
    @Test
    fun readResTest(){
        prin(Util.readFile("test_input.txt"))
    }
}