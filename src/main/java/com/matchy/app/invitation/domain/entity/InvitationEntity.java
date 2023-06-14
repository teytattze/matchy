package com.matchy.app.invitation.domain.entity;

import java.util.UUID;

public class InvitationEntity {
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private String message;
    private InvitationStatus status;
}
