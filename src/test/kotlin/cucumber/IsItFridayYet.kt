package cucumber

import com.example.sandbox.application.IsItFridayYet
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.springframework.beans.factory.annotation.Autowired


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("cucumber")
class IsItFridayYet {

    private var today: String = ""
    private var actualAnswer: String = ""

    @Autowired
    lateinit var isItFridayYet : IsItFridayYet


    //this test code will be in the production code
//    companion object IsItFriday {
//        fun isItFriday(today: String): String {
//            return if (today == "Friday") "TGIF" else "Nope"
//        }
//    }

    @Given("today is {string}")
    fun today_is(today: String) {
        this.today = today;
    }

    @When("I ask whether it's Friday yet")
    fun i_ask_whether_it_s_friday_yet() {
        actualAnswer = IsItFridayYet().isItFriday(today)
    }

    @Then("I should be told {string}")
    fun i_should_be_told(expectedAnswer: String?) {
        assertThat(expectedAnswer).isEqualTo(actualAnswer)
    }
}