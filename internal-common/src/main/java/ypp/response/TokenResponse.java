package ypp.response;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
