package uk.gov.dhsc.htbhf.browserstack;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TestOutputHtmlGeneratorTest {

    private static final String REPORT_LOCATION = "test-report.html";
    private static final String SESSION_ID = "kjhndfsd98rkbnjsdfh";
    private static final String TEST_NAME = "Test1";
    private static final int ATTEMPTS = 3;

    @Test
    void shouldOutputReportFile() throws IOException {
        try {
            //Given
            TestExecutionSummary testExecutionSummary = setupMockTestExecutionSummary();
            TestResultSummary testResultSummary = new TestResultSummary(testExecutionSummary, TEST_NAME, ATTEMPTS, SESSION_ID);
            List<TestResultSummary> resultSummaries = singletonList(testResultSummary);

            //When
            TestOutputHtmlGenerator.generateHtmlReport(resultSummaries, REPORT_LOCATION);

            //Then
            String reportContents = Files.readString(Path.of(REPORT_LOCATION));
            assertThat(reportContents).isNotBlank();
            assertThat(reportContents).startsWith("<!DOCTYPE html>");
            assertThat(reportContents).contains(
                    "<h1>Compatibility Test Summary</h1>",
                    "<td>Test1</td>",
                    "<td>true</td>",
                    "<td>00:09</td>",
                    "<td>3</td>",
                    "<td>kjhndfsd98rkbnjsdfh</td>",
                    "<td>" + testResultSummary.getFormattedStartTime() + "</td>",
                    "<td>" + testResultSummary.getFormattedEndTime() + "</td>"
            );
        } finally {
            Files.delete(Path.of(REPORT_LOCATION));
        }
    }

    private TestExecutionSummary setupMockTestExecutionSummary() {
        TestExecutionSummary testExecutionSummary = mock(TestExecutionSummary.class);
        given(testExecutionSummary.getFailures()).willReturn(Collections.emptyList());
        given(testExecutionSummary.getTimeStarted()).willReturn(100L);
        given(testExecutionSummary.getTimeFinished()).willReturn(10000L);
        given(testExecutionSummary.getTimeFinished()).willReturn(10000L);
        return testExecutionSummary;
    }

}
