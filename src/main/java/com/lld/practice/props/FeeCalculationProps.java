package com.lld.practice.props;

import java.math.BigDecimal;

public record FeeCalculationProps(
        BigDecimal hourlySubsequentMultiplier
) {
}
