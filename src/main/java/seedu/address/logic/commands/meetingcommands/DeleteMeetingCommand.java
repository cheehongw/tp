package seedu.address.logic.commands.meetingcommands;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;

public class DeleteMeetingCommand extends DeleteCommand {
    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted Meeting: %1$s";

    private MeetingTarget target;

    /**
     * @param target the {@code Index} or {@code Title} being targetted in the MeetingsBook list
     */
    public DeleteMeetingCommand(Object target) {
        assert target instanceof Title || target instanceof Index;

        if (target instanceof Title) {
            this.target = MeetingTarget.of((Title) target, null);
        } else if (target instanceof Index) {
            this.target = MeetingTarget.of((Index) target, null);
        } else {
            this.target = null;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Objects.requireNonNull(model);
        List<Meeting> lastShownList = model.getSortedAndFilteredMeetingList();
        target.setTargetList(lastShownList);
        Meeting meetingToDelete = target.targetMeeting();

        model.deleteMeeting(meetingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMeetingCommand // instanceof handles nulls
                && target.equals(((DeleteMeetingCommand) other).target)); // state check
    }


}
