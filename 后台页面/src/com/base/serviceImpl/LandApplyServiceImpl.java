package com.base.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.daoImpl.BaseInfoDaoImpl;
import com.base.daoImpl.LandApplyDaoImpl;
import com.base.daoImpl.LandInfoDaoImpl;
import com.base.daoImpl.LandLayoutDaoImpl;
import com.base.daoImpl.Land_PlantingDaoImpl;
import com.base.po.BaseInfo;
import com.base.po.LandApply;
import com.base.po.LandInfo;
import com.base.po.LandLayout;
import com.base.po.Land_Planting;
import com.base.po.Land_base;
import com.base.service.LandApplyService;

@Service("landApplyService")
public class LandApplyServiceImpl implements LandApplyService {
	
	@Autowired
	private LandApplyDaoImpl landApplyDaoImpl;
	@Autowired
	private BaseInfoDaoImpl baseInfoDaoImpl;
	@Autowired
	private LandLayoutDaoImpl landLayoutDaoImpl;
	@Autowired
	private LandInfoDaoImpl landInfoDaoImpl;
	@Autowired
	private Land_PlantingDaoImpl land_PlantingDaoImpl;

	//1.��������  2.����У��  3.����У��    
	@Override
	public List<BaseInfo> getBaseInfos(int baseType) {
		List<BaseInfo> list=baseInfoDaoImpl.getBaseInfos(baseType);
		return list;
	}
	
	public List<String> getLandLayout(int bid,String planting)
	{
		List<Land_Planting> list=land_PlantingDaoImpl.getPlanting(bid, planting);		
		List<String> str=new ArrayList<String>();	
		for(Land_Planting lp:list)
		{
			str.add(String.valueOf(lp.getLid()));
		}
		return str;
	}
	
	public List<Land_Planting> getPlanting(int bid)
	{
		List<Land_Planting> list=land_PlantingDaoImpl.getPlanting(bid);
		return list;
	}

	@Override
	public List<LandLayout> getLandLayout(int bid) {
		List<LandLayout> list=landLayoutDaoImpl.getLayout(bid);
		return list;
	}

    public List<Land_base> getLand_baseView(int lid) {
		
		return landInfoDaoImpl.getView(lid);
	}
    
    public List<Land_base> getLand_base(int bid)
    {
    	return landInfoDaoImpl.getlandbase(bid);
    }

	@Override
	public List<LandInfo> getLandInfo(int lid) {
		
		return landInfoDaoImpl.getLandInfo(lid);
	}

	@Override
	public void addLandApply(LandApply la) {
		landApplyDaoImpl.doLandApply(la);
         
	}

	//��ѯ�û��������е������¼
	@Override
	public List<LandApply> getUserApplys(String applicantId) {
		List<LandApply> list=landApplyDaoImpl.getUserApplys(applicantId);
		return list;
	}

	@Override
	public void updateUserApply(LandApply la) {
		landApplyDaoImpl.updateLandApply(la);

	}

	@Override
	public int getSpareValue(int lid) {
		// ����
		return 0;
	}

	//7.ʧЧ(�û�����ȡ��)
	@Override
	public void cancelApply(int la_id) {
		LandApply la=landApplyDaoImpl.getapply(la_id);
		la.setStatus(7);
		landApplyDaoImpl.updateLandApply(la);

	}

}
