
package com.mogikanensoftware.azure.accountsender.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String number;
    private LocalDate openDate;
    private String bankName;
    private Branch branch;
    private String profileId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Branch {
        private String branchName;
        private String branchCode;
        private String branchLocation;
    }

}