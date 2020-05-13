package galih.dj.jfood_android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<Seller> _listSeller;
    // header titles
    // child data in format of header title, child title
    private HashMap<Seller, ArrayList<Food>> _childMapping;

    public MainListAdapter(Context context, ArrayList<Seller> listSeller, HashMap<Seller, ArrayList<Food>> childMapping)
    {
        this._context = context;
        this._listSeller = listSeller;
        this._childMapping = childMapping;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
        return this._childMapping.get(this._listSeller.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_food, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        //String s = "[" + childText.getId() + "] " + childText.getName() + ", Price : " + childText.getPrice();
        //txtListChild.setText(s);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return this._childMapping.get(this._listSeller.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this._listSeller.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this._listSeller.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {
        final Seller headerTitle = (Seller) getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_seller, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getName());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}