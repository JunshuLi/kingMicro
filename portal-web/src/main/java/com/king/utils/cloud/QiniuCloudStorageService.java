package com.king.utils.cloud;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.king.common.utils.exception.RRException;
import com.king.dal.gen.model.oss.CloudStorageConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;


/**
 * 七牛云存储
 * @author King chen
 * @emai 396885563@qq.com
 * @data2018年5月23日
 */
public class QiniuCloudStorageService extends CloudStorageService {
    private UploadManager uploadManager;//上传管理
    private BucketManager bucketMgr;//删除
    private String token;
    private Auth auth;

    public QiniuCloudStorageService(CloudStorageConfig config){
        this.config = config;
         auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        //初始化
        init();
    }

    private void init(){
        uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
        bucketMgr = new BucketManager(auth, new Configuration(Zone.autoZone()));
        token = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey()).
                uploadToken(config.getQiniuBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            throw new RRException("上传文件失败，请核对七牛配置信息", e);
        }

        return config.getQiniuDomain() + "/" + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new RRException("上传文件失败", e);
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQiniuPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQiniuPrefix(), suffix));
    }

	@Override
	public void delete(String deleteObject) {
		try {
			bucketMgr.delete(config.getQiniuBucketName(), deleteObject);
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
