package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class StepBuilderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return this.jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .next(step4())
                .next(step5())
//                .next(step3())
                .build();
    }

    /**
     * TaskletStepBuilder 기본 빌더 클래스
     * @return
     */
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    /**
     * SimpleStepBuilder 청크기반의 작업을 처리하는 ChunkOrientedTasklet 클래스 생성
     * @return
     */
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<String, String>chunk(3)
                .reader(() -> null)
                .writer(list -> {})
                .build();
    }

    @Bean
    public Step step2_0() {
        return stepBuilderFactory.get("step2_0")
                .<String, String>chunk(3)
                .reader(new ItemReader<String>() {
                    @Override
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        return null;
                    }
                })
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String s) throws Exception {
                        return null;
                    }
                })
                .writer(new ItemWriter<String>() {
                            @Override
                            public void write(List<? extends String> list) throws Exception {

                            }
                        }
                )
                .build();
    }

    /**
     * PartitionStepBuilder
     * @return
     */
    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .partitioner(step1())
                .gridSize(2)
                .build();
    }

    /**
     * JobStepBuilder
     * @return
     */
    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .job(job())
                .build();
    }

    /**
     * FlowStepBuilder
     * @return
     */
    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .flow(flow())
                .build();
    }
    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
//                .next(step3())
                .build();
    }
    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(step2()).end();
        return flowBuilder.build();
    }
}
