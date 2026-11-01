package com.acmemini.statements;

import java.util.Comparator;
import java.util.List;

/** Monthly statement: the month's booked payments, oldest first, and the closing balance in cents. */
public class StatementBuilder {
    public record Line(String paymentId, String bookedOn, long amountCents) {}
    public record Statement(List<Line> lines, long closingBalanceCents) {}

    public Statement build(long openingBalanceCents, List<Line> booked) {
        List<Line> lines = booked.stream().sorted(Comparator.comparing(Line::bookedOn)).toList();
        long closing = openingBalanceCents - lines.stream().mapToLong(Line::amountCents).sum();
        return new Statement(lines, closing);
    }
}
