import states.WebStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;


import java.util.Random;


import static org.junit.Assert.assertEquals;

public class OperatorModelTest implements FsmModel {
    private Operator test = new Operator();


    private WebStates operatorModel = WebStates.LOGGED_OUT;
    private boolean signedOut, signedIn, viewSearches, viewProduct, viewCart, checkOut;

    public WebStates getState() { return operatorModel;  }

    public void reset(final boolean var1){
        operatorModel = WebStates.LOGGED_OUT;
        test.logOut();
        signedOut = true;
        signedIn = false;
        viewSearches = false;
        viewProduct = false;
        viewCart = false;
        checkOut = false;

        if(var1){
            test = new Operator();
        }
    }

    public boolean loginGuard(){  return getState().equals(WebStates.LOGGED_OUT); }
    public @Action void login(){
        test.logIn();
        signedOut = false;
        signedIn = true;
        operatorModel = WebStates.LOGGED_IN;

        assertEquals("The system under test's login state does not match the model's state",signedIn, test.isSignedIn());
    }

    public boolean logoutGuard(){ return getState().equals(WebStates.LOGGED_IN); }
    public @Action void logout(){
        test.logOut();


        signedIn = false;
        signedOut = true;
        operatorModel = WebStates.LOGGED_OUT;
        assertEquals("The system under test's logout state does not match the model's state",signedOut, test.isSignedOut());
    }

    public boolean viewSearchesGuard(){ return getState().equals(WebStates.LOGGED_IN); }
    public @Action void viewSearches(){
        test.search();

        operatorModel = WebStates.VIEW_SEARCHES;
        viewSearches = true;
        viewCart = false;
        assertEquals("The system under test's search state does not match the model's state",viewSearches, test.isViewSearches());
    }

    public boolean viewProductGuard(){ return getState().equals(WebStates.VIEW_SEARCHES); }
    public @Action void viewProduct(){
        test.viewProduct();

        operatorModel = WebStates.VIEW_PRODUCTS;
        viewSearches = false;
        viewProduct = true;
        assertEquals("The system under test's product state does not match the model's state",viewProduct, test.isViewProduct());
    }

    public boolean viewCartGuard(){ return getState().equals(WebStates.VIEW_PRODUCTS); }
    public @Action void viewCart(){
        test.viewCart();

        operatorModel = WebStates.VIEW_CART;
        viewSearches = false;
        viewProduct = false;
        viewCart = true;

        assertEquals("The system under test's cart state does not match the model's state",viewCart, test.isViewCart());
    }

    public boolean checkoutGuard(){ return (getState().equals(WebStates.VIEW_CART)); }
    public @Action void checkout(){
        test.checkOut();

        operatorModel = WebStates.CHECKOUT;
        viewCart = false;
        checkOut = true;

        assertEquals("The system under test's checkout state does not match the model's state",checkOut, test.isCheckOut());
    }




    @Test
    public void OperatorModelRunner() {
        final GreedyTester tester = new GreedyTester(new OperatorModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(250); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }


}
