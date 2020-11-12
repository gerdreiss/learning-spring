package com.github.gerdreiss;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    private Counter dummyCallCounter;
    private Timer createdDummyTimer;

    public DummyController(Counter dummyCallCounter, Timer createdDummyTimer) {
        this.dummyCallCounter = dummyCallCounter;
        this.createdDummyTimer = createdDummyTimer;
    }

    @RequestMapping(method = RequestMethod.GET)
    EntityModel<Dummy> dummy() throws Exception {
        dummyCallCounter.increment();
        return createdDummyTimer.recordCallable(() ->
                EntityModel.of(new Dummy("dummy"), //
                        linkTo(methodOn(DummyController.class).dummy()).withRel("dummy")));
    }
}
