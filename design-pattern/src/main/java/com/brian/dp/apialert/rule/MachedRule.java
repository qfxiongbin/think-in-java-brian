package com.brian.dp.apialert.rule;

import lombok.Data;

/**
 * MatchRule
 *
 * @author BrianX
 * @date 2022/09/05 17:19
 **/
@Data
public class MachedRule {
    private int maxTps;
    private int maxErrorCount;
    private int maxTimeoutTps;
}
