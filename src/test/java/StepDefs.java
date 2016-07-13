package test.java;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class StepDefs {
    private static String previousEndingPoint = "";
    private static String previousStartingPoint = "";
    private static String endingPoint;
    private static String startingPoint;
    private static GoogleDrivingDirections googleDrivingDirections;
    private static ArrayList<GoogleDrivingDirectionsLeg> routeLegs;

    @Given("^I am at \"([^\"]*)\"$")
    public void i_want_to_go_from(String startingPoint) throws Throwable {
        StepDefs.startingPoint = startingPoint;
    }

    @When("^I want to go to \"([^\"]*)\"$")
    public void to(String endingPoint) throws Throwable {
        StepDefs.endingPoint = endingPoint;
    }

    @Then("^I should see that the total Distance is \"([^\"]*)\", which should take \"([^\"]*)\"$")
    public void i_should_see_that_the_total_Distance_is(String expectedDistance, String expectedTime) throws Throwable {
        if ((!startingPoint.equals(previousStartingPoint)) || (!endingPoint.equals(previousEndingPoint))) {
            googleDrivingDirections = new GoogleDrivingDirections(startingPoint, endingPoint);
            routeLegs = googleDrivingDirections.getDirections();
            previousStartingPoint = startingPoint;
            previousEndingPoint = endingPoint;
        }
        assertThat("total distance is wrong", googleDrivingDirections.getTotalDistance(), is(expectedDistance));
        assertThat("total time is wrong", googleDrivingDirections.getTotalTime(), is(expectedTime));
    }

    @Then("^leg \"([^\"]*)\", \"([^\"]*)\", for \"([^\"]*)\", which should take \"([^\"]*)\" is returned$")
    public void leg_for_which_should_take_is_returned(int leg, String instruction, String timeToTravel, String distanceToTravel) throws Throwable {
        assertThat("leg is wrong", routeLegs.get(leg-1).getLeg(), is(leg));
        assertThat("instruction is wrong", routeLegs.get(leg-1).getDirection(), is(instruction));
        assertThat("timeToTravel is wrong", routeLegs.get(leg-1).getDuration(), is(timeToTravel));
        assertThat("distanceToTravel is wrong", routeLegs.get(leg-1).getDistance(), is(distanceToTravel));
    }
}
