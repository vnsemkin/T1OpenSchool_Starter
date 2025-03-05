package org.vnsemkin.t1openshcool_starter.dto;

import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder
public record ResultDTO(@NonNull String message) {}
