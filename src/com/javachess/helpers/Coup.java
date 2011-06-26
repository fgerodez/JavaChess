package com.javachess.helpers;

import com.javachess.modele.plateau.Case;

public class Coup {
	private Case caseSource;
	private Case caseDestination;
	
	public Coup(Case caseSource, Case caseDestination) {
		this.caseSource = caseSource;
		this.caseDestination = caseDestination;
	}
	
	public Case getCaseSource() {
		return caseSource;
	}
	
	public void setCaseSource(Case caseSource) {
		this.caseSource = caseSource;
	}
	
	public Case getCaseDestination() {
		return caseDestination;
	}
	
	public void setCaseDestination(Case caseDestination) {
		this.caseDestination = caseDestination;
	}
}
