package org.cia.committee.domain.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventBuilderTest {


    @Test
    void testEventBuilderCreation() {
        EventBuilder<String> builder = EventBuilder.create();
        assertNotNull(builder);
    }
    @Test
    public void testEventBuilderDetails() {
        EventBuilder<String> builder = EventBuilder.create();
        String eventData = "Event data";
        builder.details(eventData);
        assertEquals(eventData, builder.build().getDetails());
    }

    @Test
    public void testEventBuilderType() {
        EventBuilder<String> builder = EventBuilder.create();
        EventType eventType = EventType.PROBLEM_CREATED;
        builder.type(eventType);
        assertEquals(eventType, builder.build().getType());
    }

}