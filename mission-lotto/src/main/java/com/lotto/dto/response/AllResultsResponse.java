package com.lotto.dto.response;

import java.util.List;

public record AllResultsResponse(
        List<UserResponse> userResults
) {
}
