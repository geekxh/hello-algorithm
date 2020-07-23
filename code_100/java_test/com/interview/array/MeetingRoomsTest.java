package com.interview.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MeetingRoomsTest {

    @Test
    public void testDifferentCases() {
        MeetingRooms meetingRooms = new MeetingRooms();
        MeetingRooms.Interval[] interval = new MeetingRooms.Interval[4];
        interval[0] = new MeetingRooms.Interval(0,3);
        interval[1] = new MeetingRooms.Interval(2,5);
        interval[2] = new MeetingRooms.Interval(6,8);
        interval[3] = new MeetingRooms.Interval(8,10);
        Assert.assertEquals(2, meetingRooms.minMeetingRooms(interval));
        Assert.assertEquals(2, meetingRooms.minMeetingRooms1(interval));
    }
}
