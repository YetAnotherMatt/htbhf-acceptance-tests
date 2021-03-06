package uk.gov.dhsc.htbhf.page;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CheckAnswersRowData {
    private String header;
    private String value;
    private CheckAnswersAction action;

    public boolean hasChangeLinkAndHeaderText() {
        return hasChangeLink() && hasHeaderText();
    }

    private boolean hasChangeLink() {
        return action.getText().equals("Change");
    }

    public boolean hasHeaderText() {
        return header.trim().length() > 0;
    }
}
