package com.example.inceptive.navigation;

/**
 * Created by inceptive on 2/8/18.
 */

public class CountModel
{

  public int approvedLeaves;
    public int rejectedLeaves;
    public int pendingLeaves;

  public int count;

  public int getApprovedLeaves() {
    return approvedLeaves;
  }

  public int getRejectedLeaves() {
    return rejectedLeaves;
  }

  public int getPendingLeaves() {
    return pendingLeaves;
  }

  public void setApprovedLeaves(int approvedLeaves) {
    this.approvedLeaves = approvedLeaves;
  }

  public void setPendingLeaves(int pendingLeaves) {
    this.pendingLeaves = pendingLeaves;
  }


  public void setRejectedLeaves(int rejectedLeaves) {
    this.rejectedLeaves = rejectedLeaves;
  }
}


