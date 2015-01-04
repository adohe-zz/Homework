package com.xqbase.java.impl.visitor;

import com.xqbase.java.Position;
import com.xqbase.java.Visitor;

/**
 * A default String Visitor.
 *
 * @author Tony He
 */
public class StringVisitor<E> implements Visitor<E> {

    @Override
    public void visit(Position<E> p) {
        System.out.println(p.element().toString());
    }
}
