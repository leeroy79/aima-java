package aima.test.core.unit.logic.fol.inference;

import org.junit.Test;

import aima.core.logic.fol.inference.FOLBCAsk;
import aima.test.core.unit.logic.fol.CommonFOLInferenceProcedureTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import aima.core.logic.fol.inference.InferenceProcedure;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.inference.proof.Proof;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.kb.FOLKnowledgeBaseFactory;
import aima.core.logic.fol.parsing.ast.Constant;
import aima.core.logic.fol.parsing.ast.Function;
import aima.core.logic.fol.parsing.ast.NotSentence;
import aima.core.logic.fol.parsing.ast.Predicate;
import aima.core.logic.fol.parsing.ast.Term;
import aima.core.logic.fol.parsing.ast.TermEquality;
import aima.core.logic.fol.parsing.ast.Variable;
import aima.core.logic.fol.domain.DomainFactory;
import aima.core.logic.fol.domain.FOLDomain;


public class LeeTests extends CommonFOLInferenceProcedureTests {

   
   

    @Test
	public void testDefiniteClauseKBKingsQueryCriminalXFalse() {

        InferenceProcedure infp = new FOLBCAsk();
        FOLDomain domain = new FOLDomain();

        domain.addPredicate("Alive");
        domain.addPredicate("Dead");
        domain.addPredicate("IsMammal");
        
        domain.addConstant("Lee");
        domain.addConstant("MichaelJackson");
        domain.addConstant("Ripley");
        
        domain.addFunction("DogOf");

        FOLKnowledgeBase kb = new FOLKnowledgeBase(domain, infp);

        kb.tell("(Alive(x) => NOT(Dead(x)))");
        kb.tell("(Dead(x) => NOT(Alive(x)))");
        kb.tell("Alive(DogOf(Lee))");
        kb.tell("Dead(MichaelJackson)");
        kb.tell("IsMammal(Ripley)");
       
        
        InferenceResult answer;
        
        answer = kb.ask("Alive(MichaelJackson)");
        Assert.assertFalse(answer.isTrue());
        Assert.assertTrue(answer.isPossiblyFalse());

        answer = kb.ask("Dead(DogOf(Lee))");
        Assert.assertFalse(answer.isTrue());
        Assert.assertTrue(answer.isPossiblyFalse());

        answer = kb.ask("IsMammal(Ripley)");
        Assert.assertTrue(answer.isTrue());
        Assert.assertFalse(answer.isPossiblyFalse());

      
	}

 

}