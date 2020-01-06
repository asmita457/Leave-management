

package com.example.inceptive.navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inceptive.fragment.ManagerHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inceptive on 2/8/18.
 */

public class ManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int OPTIONAL_VIEW = 1;

    Context ctx;
    String manage_leave_id;
    String leave_Id;
    String approved_by;
    String from_date,to_date;
    ArrayList<viewList> vwlist;
    String s_leave_id;
    Spinner spinnerdrop;
    UpdateInter updateinter;
    managerUpdate managerupdate;
    ArrayList<SpinnerModel> statusleave;




    private final String  TAG = "  ViewAdapter  " ;

    public ManagerAdapter(Context context, ArrayList<viewList> viewlist, String leave_status_id, String from_date, String to_date, ArrayList<SpinnerModel> statusleave, managerUpdate managerupdate, UpdateInter updateinter)
    {
        this.ctx =context;
        this.vwlist = viewlist;
        this.from_date = from_date;
        this.to_date = to_date;
        this.statusleave = statusleave;
        this.managerupdate=managerupdate;
        this.updateinter=updateinter;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        if (viewType == OPTIONAL_VIEW) {
            if(vwlist.isEmpty())
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opt, parent, false);
                OptionalViewHolder vh = new OptionalViewHolder(v);
                return vh;
            }
            else if(vwlist.size()>0 || vwlist.size()==0)
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.optional, parent, false);
                OptionalViewHolder vh = new OptionalViewHolder(v);
                return vh;
            }
        }
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_leave, parent, false);
        My_ViewHolder vh = new My_ViewHolder(v);
        return vh;

//        return new ViewAdapter.My_ViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.view_leave, parent, false) ) ;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        try {
            if (holder instanceof ManagerAdapter.My_ViewHolder)
            {

              final  viewList singleItem  = vwlist.get(position);
//                ((My_ViewHolder) holder).usrid.setText(singleItem.getUser_id());
                       ((My_ViewHolder) holder).first_name.setText( singleItem.getFirst_name() );
                       ((My_ViewHolder) holder).last_name.setText( singleItem.getLast_name() );
                       ((My_ViewHolder) holder).linearlayoutview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        viewList viewList = vwlist.get(position);
//                        Intent i = new Intent(x.this, y.class);
//                        startActivity(i);
                        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx, R.style.AlertDialogTheme);
                        LayoutInflater inflater;

                        inflater = LayoutInflater.from(ctx);
                        final View dialogView = inflater.inflate(R.layout.activity_detailed_infor, null);
                        dialogBuilder.setView(dialogView);

                        final AlertDialog alert =dialogBuilder.create();
                        alert.setTitle("Leave Details");
                        alert.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                updateinter.getData();
                            }
                        });
                        alert.setOnShowListener( new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface arg0) {
//                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                            }
                        });
                        alert.show();

//                            TextView user_id=(TextView) dialogView.findViewById(R.id.usrid);
                           TextView viewleave_fromdate = (TextView) dialogView.findViewById(R.id.viewleave_fromdate);
                          TextView viewleave_todate = (TextView) dialogView.findViewById(R.id.viewleave_todate);
                          final TextView reason = (TextView)dialogView.findViewById(R.id.reason);
                        TextView man_leaveid=(TextView)dialogView.findViewById(R.id.man_leaveid);
                        TextView remain_leave=(TextView)dialogView.findViewById(R.id.remain_leave);
                        TextView man_leavecount=(TextView)dialogView.findViewById(R.id.man_leavecount);
                         final Spinner spinnerdrop =(Spinner) dialogView.findViewById(R.id.spinner);

                        LinearLayout detailprof=(LinearLayout)dialogView.findViewById(R.id.detailprof);

                        reason.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                viewList viewList = vwlist.get(position);
                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx,R.style.AlertDialogTheme);
                                LayoutInflater inflater;

                                inflater = LayoutInflater.from(ctx);
                                final View dialogView = inflater.inflate(R.layout.rsn,null );
                                dialogBuilder.setView(dialogView);

                                final AlertDialog alert =dialogBuilder.create();
                                alert.setTitle("Leave Reason");
                                alert.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

//                                        updateinter.getData();
                                    }
                                });
                                alert.setOnShowListener( new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface arg0) {
//                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                                    }
                                });
                                alert.show();


                                TextView rssnn=(TextView)alert.findViewById(R.id.rssnn);
                                rssnn.setText(viewList.getReason());
                                rssnn.setMaxLines(7);

//
//                                final AppCompatDialog dialog = new AppCompatDialog(ctx);
//                                dialog.setContentView(R.layout.rsn);
//                                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                                TextView txtterms = (TextView) dialog.findViewById(R.id.rssnn);
//                                Button buttonok = (Button) dialog.findViewById(R.id.buttonok);
//
//                                txtterms.setText(viewList.getReason());
////                                termsconditions.setText(getString(R.string.TermsConditions));
////
////                                termsconditions.setMovementMethod(new ScrollingMovementMethod());
//                                dialog.setCanceledOnTouchOutside(true);
//
//                                buttonok.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        dialog.dismiss();
//                                    }
//                                });
//                                dialog.show();


                            }



                        });

//                        Button btnnn=(Button)dialogView.findViewById(R.id.update);

//                        user_id.setText(viewList.getUser_id());
                        viewleave_fromdate.setText(viewList.getFrom_date());
                        reason.setText(viewList.getReason());
                        viewleave_todate.setText(viewList.getTo_date());
                        man_leaveid.setText(viewList.getManager_leaveid());
                        Log.e("Remaining Count",viewList.getRemaining_count()+"");
                        remain_leave.setText(viewList.getRemaining_count()+"");
                        man_leavecount.setText(viewList.getLeave_count());
                        List<String> allstatus=new ArrayList<String>();


                        for(int i = 0;i<statusleave.size() ;i++)
                        {
                            String lvstatus=statusleave.get(i).getLeave_status();
                            allstatus.add(lvstatus);
                        }

                        spinnerdrop.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, allstatus));
                        spinnerdrop.setSelected( false);
                        spinnerdrop.setSelection(0 , true );


             spinnerdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        viewList item;

                        if(vwlist.size()==1)
                        {
                             item = vwlist.get(0);
                        }
                        else
                        {
                             item = vwlist.get(position);
                        }


                        String mlid=singleItem.getManager_leaveid();
                        Log.e(TAG , "ViewListData : "+ item.getManager_leaveid() + item.getFirst_name() ) ;


                        String leave_st =spinnerdrop.getSelectedItem().toString();
                        if(leave_st.equals("Pending"))
                        {
                            leave_Id = "1";
                        }
                        else if (leave_st.equals("Approved"))
                        {
//                            Toast.makeText(ctx,"Status Approved is Updated",Toast.LENGTH_SHORT).show();
                            leave_Id = "2";
                        }
                        else if(leave_st.equals("Rejected"))
                        {
//                            Toast.makeText(ctx,"Status Rejected is Updated",Toast.LENGTH_SHORT).show();
                            leave_Id = "3";
                        }
                        updateinter.updateR(leave_Id, mlid);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
//
                    }
                });
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    private void go() {

        Log.e("Hello....",".....");
    }


    public int getItemViewType(int position) {
        if (vwlist.size() == 0) {
            return OPTIONAL_VIEW;
        }
        return super.getItemViewType(position);
    }




    @Override
    public int getItemCount() {
        if (vwlist.size() == 0) {
            return OPTIONAL_VIEW;

        } else {
            return vwlist.size();
        }
    }

    public void FilterList(ArrayList<viewList> filteredList) {

        vwlist=filteredList;
        notifyDataSetChanged();
    }

    public class My_ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView last_name;
        public TextView first_name,usrid;
        public TextView viewleave_empid;
        public LinearLayout linearlayoutview,detailprof;
        Button btn;

        public My_ViewHolder(View itemView)
        {
            super(itemView);

            viewleave_empid = (TextView) itemView.findViewById(R.id.man_leaveid);
            linearlayoutview=(LinearLayout)itemView.findViewById(R.id.linearlayoutview);
            first_name=(TextView)itemView.findViewById(R.id.fname);
            last_name=(TextView)itemView.findViewById(R.id.lname);
//            detailprof=(LinearLayout)itemView.findViewById(R.id.detailprof);
//            usrid=(TextView)itemView.findViewById(R.id.usrid);

//            btn=(Button)itemView.findViewById(R.id.update);


        }
    }

    public class OptionalViewHolder extends RecyclerView.ViewHolder {
        TextView noRecordFound;

        OptionalViewHolder(View itemView) {
            super(itemView);
            noRecordFound = (TextView) itemView.findViewById(R.id.noRecordFound);
        }
    }


}
