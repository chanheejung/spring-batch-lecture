package io.springbatch.springbatchlecture;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");

        /**
         * Step3에서 실패했을때
         * 다시 Job을 시작하면 기존 성공한 Step은 건너띄고 Step3부터 실행하는지 확인
         */
        if(name == null){
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
//            throw new RuntimeException("step3 has failed");
        }

        return RepeatStatus.FINISHED;
    }
}
