package com.javainuse.main;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.javainuse.model.Counter;

public class DroolsTest {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");

			Counter cnt1 = new Counter(1, "cnt1");
			Counter cnt2 = new Counter(2, "cnt2");

			System.out.println();
			System.out.println("fire rules after inserting counter1");
			System.out.println();
			kSession.insert(cnt1);
			//fire rules with counter1
			kSession.fireAllRules();

			System.out.println();
			System.out.println("fire rules after inserting counter2");
			System.out.println();
			kSession.insert(cnt2);
			//fire rules with already existing counter1 and newly inserted counter2
			kSession.fireAllRules();

			//Dispose the session at the end.
			kSession.dispose();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
