package ninja.sef.simpleindex;

import ninja.sef.simpleindex.index.IndexCreatorOperation;

public class OperationFactory {
    public static Operation createOperation(String operatorSwitch) {
        
        if("-buildindex".equals(operatorSwitch)) {
            return new IndexCreatorOperation();
        }
        return null;
    }
}
