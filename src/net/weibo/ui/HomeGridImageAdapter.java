package net.weibo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeGridImageAdapter extends BaseAdapter {
	
	private Context context;
	
	public HomeGridImageAdapter(Context context){
		this.context = context;
	}
	
	private Integer[] images = {
			R.drawable.icon1,
			R.drawable.icon2,
			R.drawable.icon3,
			R.drawable.icon4,
			R.drawable.icon5,
			R.drawable.icon6,
			R.drawable.icon7,
			R.drawable.icon8,
			R.drawable.icon9
	};
	private String[] texts = {
			"国内新闻",
			"国际新闻",
			"财经新闻",
			"娱乐新闻",
			"房产楼市",
			"汽车世界",
			"科技",
			"游戏",
			"微博"
			
	};

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		ImgTextWrapper wrapper;
		if(view==null) {  
			   wrapper = new ImgTextWrapper();  
			   LayoutInflater inflater = LayoutInflater.from(context);  
			   view = inflater.inflate(R.layout.imageview, null);  
			   view.setTag(wrapper);  
			   view.setPadding(10, 10, 10, 10);   
			  } else {  
			   wrapper = (ImgTextWrapper)view.getTag();  
			  }  
			    
			  wrapper.imageView = (ImageView)view.findViewById(R.id.home_image);  
			  wrapper.imageView.setBackgroundResource(images[position]);  
			  wrapper.textView = (TextView)view.findViewById(R.id.home_image_text);  
			  wrapper.textView.setText(texts[position]);  
			    
			  return view;
	}
	
	class ImgTextWrapper {  
		 ImageView imageView;  
		 TextView textView;  
		   
		} 

}
