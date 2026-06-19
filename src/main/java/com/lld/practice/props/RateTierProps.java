package com.lld.practice.props;

import com.lld.practice.enums.RateTier;
import com.lld.practice.enums.VehicleType;

import java.util.Map;

public record RateTierProps(Map<VehicleType, RateTier> config)
{ }